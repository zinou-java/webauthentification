package dz.gouv.mesrs.dao;

import dz.gouv.mesrs.model.Identifiable;

import java.io.Serializable;
import java.util.List;



/**
 * @description Interface commune pour les DAO
 */
public interface CommonDao<CommonObject extends Identifiable<KeyType>, KeyType extends Serializable> {

	public void refresh(CommonObject obj);

	public void persist(CommonObject obj);

	public CommonObject merge(CommonObject obj);

	public CommonObject findById(KeyType id);

	public List<CommonObject> findAll();

	public void remove(CommonObject obj);

	public List<CommonObject> findByQuery(String query);

	public List<CommonObject> findAllByExample(CommonObject example);

	public List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode);

	public int countAllByExample(CommonObject example);

	public int countAllByExample(CommonObject example, SearchMode searchMode);

	public List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode);

	public int countAllByKeyWord(String keyWord);

	public CommonObject findUniqueByExample(CommonObject example);

	public CommonObject findUniqueOrNoneByExample(CommonObject example);

	public CommonObject save(CommonObject object);

	public int countAllByKeyWord(String keyWord, SearchMode searchMode);

	public boolean exists(@SuppressWarnings("rawtypes") Class clazz, String idKey, Object idValue);

}