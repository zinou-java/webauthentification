package dz.gouv.mesrs.services.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.SearchMode;
import dz.gouv.mesrs.model.Identifiable;
import dz.gouv.mesrs.services.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Service("commonService")
@Transactional
public abstract class CommonServiceImpl<CommonObject extends Identifiable<KeyType>, KeyType extends Serializable>
		implements CommonService<CommonObject,  KeyType> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	


	protected CommonDao<CommonObject, KeyType> commonDao = getDao();

    protected abstract CommonDao<CommonObject, KeyType> getDao();

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public CommonObject findById(KeyType id) {
		CommonObject obj = getDao().findById(id);

		if (obj != null)
			return obj;
		return null;
	}
	


	@Override
	@Transactional
	public void persist(CommonObject dto) {
		Assert.notNull(dto, "can't persist a null dto");

		Exception exception = null;
		try {


			getDao().persist(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	@Transactional
	public CommonObject merge(CommonObject dto) {
		Assert.notNull(dto, "can't merge a null dto");
		Exception exception = null;

		if (dto != null) {
			try {


				getDao().merge(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (exception != null) {
			logger.error("An error occured while merging the Dto {}", new Object[] { dto });
			throw new RuntimeException(exception);
		}
		return dto;

	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAll() {
		return getDao().findAll();
	}



	// TODO journalisation !!!
	@Override
	@Transactional
	public void remove(CommonObject dto) {
		Assert.notNull(dto, "can't delete a null dto");
		getDao().remove(dto);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example) {
	return getDao().findAllByExample(example);

	}



	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode) {

	return  getDao().findAllByExample(example, searchMode);
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example) {

		return getDao().countAllByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonObject findUniqueByExample(CommonObject example) {
		return getDao().findUniqueByExample(example);
	}







	@Transactional(readOnly = true)
	@Override
	public int countByKeyWord(String keyWord) {
		return getDao().countAllByKeyWord(keyWord);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode) {
		return  getDao().findAllByKeyWord(keyWord, searchMode);

	}

	@Transactional
	@Override
	public CommonObject save(CommonObject dto) {
		Assert.notNull(dto, "can't persist a null dto");

		return  getDao().save(dto);

	}



	@SuppressWarnings("unchecked")
	private Class<CommonObject> getCmmonObjectClass() {
		return (Class<CommonObject>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	private Class<CommonObject> getCommonObjectClass() {
		return (Class<CommonObject>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@SuppressWarnings("unchecked")
	private Class<KeyType> getKeyTypeClass() {
		return (Class<KeyType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
	}



	@Transactional(readOnly = true)
	@Override
	public int countByKeyWord(String keyWord, SearchMode searchMode) {
		return getDao().countAllByKeyWord(keyWord, searchMode);
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example, SearchMode searchMode) {

		return getDao().countAllByExample(example, searchMode);
	}


	

}