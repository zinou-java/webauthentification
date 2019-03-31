package dz.gouv.mesrs.dao.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.SearchMode;
import dz.gouv.mesrs.dao.SearchMode.Filter;
import dz.gouv.mesrs.dao.SearchMode.FilterMode;
import dz.gouv.mesrs.dao.SearchMode.SortField;
import dz.gouv.mesrs.dao.SearchMode.SortOrder;
import dz.gouv.mesrs.model.Identifiable;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description Classe d'implementation de l'interface commune pour les DAO
 */
@Repository("commonDao")
public abstract class CommonDaoImpl<CommonObject extends Identifiable<KeyType>, KeyType extends Serializable>
		implements CommonDao<CommonObject, KeyType> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	public EntityManager entityManager;

	protected Class<CommonObject> domainClass = getDomainClass();
	protected abstract Class<CommonObject> getDomainClass();

	public CommonDaoImpl() {
		super();
	}

	@Override
	@Transactional
	public void refresh(CommonObject obj) {
		entityManager.refresh(obj);
	}

	@Override
	@Transactional
	public void persist(CommonObject obj) {
		logger.debug("persisting commonobject instance");
		try {
			entityManager.persist(obj);
			entityManager.flush();
			entityManager.refresh(obj);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public CommonObject merge(CommonObject obj) {
		logger.debug("merging commonObject instance");
		try {
			logger.debug("merge successful");
			return entityManager.merge(obj);
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public CommonObject findById(KeyType id) {
		logger.debug("getting Common instance with id: " + id);
		try {
			CommonObject instance = entityManager.find(domainClass, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public List<CommonObject> findAll() {
		try {
			String query = "select obj from " + domainClass.getSimpleName() + "  obj";

			Query qObject = entityManager.createQuery(query);
			List<CommonObject> instanceList = qObject.getResultList();
			logger.debug("get successful");
			return instanceList;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public void remove(CommonObject persistentInstance) {
		logger.debug("removing CommonObject instance:" + domainClass.toString());
		try {
			persistentInstance=entityManager.merge(persistentInstance);
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example) {
		Criteria criteria = getCriteria(example);
		List<CommonObject> l = criteria.list();
		return l;
		// return getCriteria(example).list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode) {
		if (searchMode == null) {
			return this.findAllByExample(example);
		}
		Criteria criteria = getCriteria(example);
		enrichCriteriaWithSearchMode(criteria, searchMode);

		return criteria.list();
	}

	protected void enrichCriteriaWithSearchMode(Criteria criteria, SearchMode searchMode) {
		if (searchMode.getPageSize() != null) {
			criteria.setMaxResults(searchMode.getPageSize());
			criteria.setFirstResult(searchMode.getStartIndex());
		}

		for (SortField sortField : searchMode.getSortFields()) {
			if (SortOrder.DESC.equals(sortField.getOrder())) {
				criteria.addOrder(Order.desc(sortField.getFieldName()));
			} else {
				criteria.addOrder(Order.asc(sortField.getFieldName()));
			}

		}

		try {
			if (searchMode.isDisjunction()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (Filter filter : searchMode.getFilters()) {
					enrichCriteriaWithFilter(disjunction, criteria, filter);
				}
				criteria.add(disjunction);
			} else {
				for (Filter filter : searchMode.getFilters()) {
					enrichCriteriaWithFilter(criteria, criteria, filter);
				}
			}
		} catch (Exception e) {
			logger.error("Une erreur est survenue lors de la construction de la requete", e);
			throw new RuntimeException("Une erreur est survenue lors de la construction de la requete", e);
		}
	}

	protected void enrichCriteriaWithFilter(Object criteria, Criteria rootCreteria, Filter filter)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		Assert.notNull(criteria, "filter doit etre non null");
		Assert.notNull(filter, "criteria doit etre non null");

		Class<?> dynamicClass = null;
		if (criteria instanceof Criteria)
			dynamicClass = Class.forName(Criteria.class.getName());
		if (criteria instanceof Disjunction)
			dynamicClass = Class.forName(Disjunction.class.getName());
		if (criteria instanceof Conjunction)
			dynamicClass = Class.forName(Disjunction.class.getName());

		Object value1 = filter.getValue1();
		Object value2 = filter.getValue2();
		Object[] values = filter.getValues();
		String propertyName = filter.getFieldName();

		Method addMethod = dynamicClass.getMethod("add", Criterion.class);

		// add alias si propertyName est de type parent.child.field ,
		// parent.child1.child2....childn.fild
		List<String> listAlias = findAliasNames(propertyName);
		if (listAlias != null && !listAlias.isEmpty()) {
			// Method createAliasMethod =
			// dynamicClass.getMethod("createAlias",
			// String.class, String.class);
			// createAliasMethod.invoke(createAliasMethod, entityName,
			// entityName);
			String newAlias = null;
			String newAlias2 = null;
			for (String alias : listAlias) {
				newAlias = alias;
				// ajouter les alias - remplacer les . par _ ds newAlias
				int index = alias.lastIndexOf(".");
				int lindex = alias.indexOf(".");
				if (index != -1 && index != lindex) {
					String str = alias.substring(0, index);
					if (str != null && str.contains(".")) {
						//str = str.replaceAll("\\.", "_");
						//newAlias = alias.replace(str, alias.replaceAll("\\.", "_"));
						newAlias = alias.replace(str, str.replaceAll("\\.", "_"));
					} else {
						newAlias = alias.replaceAll("\\.", "_");
					}


					
					
				}
				
				newAlias2 = alias.replaceAll("\\.", "_");
				propertyName = propertyName.replace(newAlias, newAlias2);
				
				createAliasIfNotAlreadyExist(rootCreteria, newAlias, newAlias2);
			}
		}

		switch (filter.getMode()) {
		case BETWEEN:
			addMethod.invoke(criteria, Restrictions.between(propertyName, value1, value2));
			break;

		case GREATER:
			addMethod.invoke(criteria, Restrictions.gt(propertyName, value1));
			break;

		case LESS:
			addMethod.invoke(criteria, Restrictions.lt(propertyName, value1));
			break;

		case GREATER_OR_EQUAL:
			addMethod.invoke(criteria, Restrictions.ge(propertyName, value1));
			break;

		case LESS_OR_EQUAL:
			addMethod.invoke(criteria, Restrictions.le(propertyName, value1));
			break;

		case IS_NULL:
			addMethod.invoke(criteria, Restrictions.isNull(propertyName));
			break;

		case IS_NOT_NULL:
			addMethod.invoke(criteria, Restrictions.isNotNull(propertyName));
			break;
		case NOT:
			addMethod.invoke(criteria,
					Restrictions.or(Restrictions.ne(propertyName, value1), Restrictions.isNull(propertyName)));
			break;

		case IN:
			addMethod.invoke(criteria, Restrictions.in(propertyName, values));
			break;

		case NOT_IN:
			addMethod.invoke(criteria, Restrictions.not(Restrictions.in(propertyName, values)));
			break;

		case EQUALS:
			addMethod.invoke(criteria, Restrictions.eq(propertyName, value1));
			break;

		case LIKE:
			if (value1 != null && !((String) value1).isEmpty()) {

				if (filter.getMatchMode() != null) {

					switch (filter.getMatchMode()) {
					case EXACT:
						addMethod.invoke(criteria, Restrictions.like(propertyName, (String) value1,
								org.hibernate.criterion.MatchMode.EXACT));
						break;
					case START:
						addMethod.invoke(
								criteria,
								Restrictions.like(propertyName, (String) value1,
										org.hibernate.criterion.MatchMode.START).ignoreCase());
					case END:
						addMethod.invoke(criteria,
								Restrictions.like(propertyName, (String) value1, org.hibernate.criterion.MatchMode.END)
										.ignoreCase());
						break;

					default:
						addMethod.invoke(
								criteria,
								Restrictions.like(propertyName, (String) value1,
										org.hibernate.criterion.MatchMode.ANYWHERE).ignoreCase());
						break;
					}
				} else
					addMethod.invoke(criteria, Restrictions.like(propertyName, value1).ignoreCase());
			}
			break;

		case OR:
		case AND:
			if (!(value1 instanceof List)) {
				break;
			}
			Junction junction = FilterMode.AND.equals(filter.getMode()) ? Restrictions.conjunction() : Restrictions
					.disjunction();
			for (Object o : ((List) value1)) {
				if (o instanceof Filter) {
					enrichCriteriaWithFilter(junction, rootCreteria, (Filter) o);
				}
			}
			addMethod.invoke(criteria, junction);
			break;

		default:
			break;
		}
	}

	private String createAliasIfNotAlreadyExist(Criteria criteria, String associationPath, String newAlias) {
		Assert.notNull(criteria);
		Assert.notNull(associationPath);
		Assert.notNull(newAlias);

		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
		@SuppressWarnings("unchecked")
		Iterator<Subcriteria> it = criteriaImpl.iterateSubcriteria();
		boolean aliasAlreadyExists = false;
		String alias = null;
		while (it.hasNext()) {
			Subcriteria subCriteria = it.next();
			if (associationPath.equals(subCriteria.getPath()) || newAlias.equals(subCriteria.getAlias())) {
				aliasAlreadyExists = true;
				alias = subCriteria.getAlias(); // l'alias
				break;
			}
		}
		if (!aliasAlreadyExists) {
			// criteria.createAlias(associationPath, newAlias);
			criteria.createAlias(associationPath, newAlias, CriteriaSpecification.LEFT_JOIN);
			criteria.setFetchMode(associationPath, FetchMode.JOIN);
			// criteria.createAlias(associationPath, newAlias,
			// CriteriaSpecification.LEFT_JOIN);
			// criteria.setFetchMode(associationPath, FetchMode.JOIN);
			alias = newAlias;
		}
		return alias;
	}

	private List<String> findAliasNames(String propertyName) {
		Assert.notNull(propertyName, "propertyName doit etre non null");
		List<String> alias = null;
		if (propertyName.contains(".")) {
			String[] result = propertyName.split("\\.");

			alias = new ArrayList<String>();
			int length = result.length - 1;
			StringBuilder ss = new StringBuilder(result[0]);
			alias.add(ss.toString());
			for (int i = 1; i < length; i++) {
				ss = ss.append(".").append(result[i]);
				alias.add(ss.toString());
			}
		}

		return alias;
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example) {
		Criteria criteria = getCriteria(example);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		Long result = (Long) criteria.uniqueResult();
		return result != null ? result.intValue() : 0;
	}

	@Override
	@Transactional(readOnly = true)
	public CommonObject findUniqueByExample(CommonObject example) {
		// Version optimisee
		CommonObject result = findUniqueOrNoneByExample(example);
		if (result == null) {
			throw new InvalidDataAccessApiUsageException("Developper, you expected one result but we found none.");
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public CommonObject findUniqueOrNoneByExample(CommonObject example) {
		// Version optimisee
		Criteria criteria = getCriteria(example);
		criteria.setFirstResult(0);
		criteria.setMaxResults(2); // !important
		@SuppressWarnings("unchecked")
		List<CommonObject> results = criteria.list();
		if (results == null || results.isEmpty()) {
			return null;
		}

		if (results.size() > 1) {
			throw new InvalidDataAccessApiUsageException("Developper, you expected at most one result but we found "
					+ String.valueOf(results.size()));
		}

		return results.iterator().next();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		if (searchMode != null) {
			enrichCriteriaWithSearchMode(criteria, searchMode);
		}
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByKeyWord(String keyWord) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		Long result = (Long) criteria.uniqueResult();
		return result != null ? result.intValue() : 0;
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example, SearchMode searchMode) {
		Criteria criteria = getCriteria(example);
		enrichCriteriaWithSearchMode(criteria, searchMode);
		criteria.setProjection(Projections.rowCount());

		criteria.setFirstResult(new Integer(0));

		// clean sort order from creteria!
		@SuppressWarnings("unchecked")
		Iterator<OrderEntry> orderIter = ((CriteriaImpl) criteria).iterateOrderings();
		while (orderIter.hasNext()) {
			orderIter.next();
			orderIter.remove();
		}

		Long result = (Long) criteria.uniqueResult();
		return result != null ? result.intValue() : 0;
	}

	@Override
	public CommonObject save(CommonObject object) {
		if (object.getIdenfiant() == null) {
			entityManager.persist(object);
			// forcing flush mode and refresh the object (@author: MM)
			entityManager.flush();
			entityManager.refresh(object);
		} else {
			object = entityManager.merge(object);
		}
		return object;
	}

	protected Criteria getCriteriaForKeyWord(String keyWord) {
		throw new IllegalStateException("Developper, you must specify the Criteria For key word search !");
	}

	protected Criteria getCriteria(CommonObject example) {
		Criteria criteria = getSession().createCriteria(example.getClass()).add(Example.create(example));
		if (example.getIdenfiant() != null) {
			criteria.add(Restrictions.eq(example.getIdentifiantName(), example.getIdenfiant()));
		}

		// fix duplicate results (MM)
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	protected Session getSession() {
		HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		return hem.getSession();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByKeyWord(String keyWord, SearchMode searchMode) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		enrichCriteriaWithSearchMode(criteria, searchMode);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));

		// clean sort order from creteria!
		@SuppressWarnings("unchecked")
		Iterator<OrderEntry> orderIter = ((CriteriaImpl) criteria).iterateOrderings();
		while (orderIter.hasNext()) {
			orderIter.next();
			orderIter.remove();
		}

		Long result = (Long) criteria.uniqueResult();
		return result == null ? 0 : result.intValue();
	}
	
	@Override
	public boolean exists(Class clazz, String idKey, Object idValue) {
	    return getSession().createCriteria(clazz)
	            .add(Restrictions.eq(idKey, idValue))
	            .setProjection(Projections.property(idKey))
	            .uniqueResult() != null;
	}
}
