package dz.gouv.mesrs.services;

import dz.gouv.mesrs.dao.SearchMode;
import dz.gouv.mesrs.model.Identifiable;

import java.io.Serializable;
import java.util.List;

public interface CommonService<CommonObject extends Identifiable<keyType>, keyType extends Serializable> {

	CommonObject findById(keyType id);
	

	void persist(CommonObject dto);

	CommonObject merge(CommonObject dto);

	List<CommonObject> findAll();


	void remove(CommonObject dto);

	List<CommonObject> findAllByExample(CommonObject example);


	List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode);

	int countAllByExample(CommonObject example);


	CommonObject findUniqueByExample(CommonObject example);


	int countByKeyWord(String keyWord);

	List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode);

	CommonObject save(CommonObject dto);


	int countAllByExample(CommonObject example, SearchMode searchMode);

	int countByKeyWord(String keyWord, SearchMode searchMode);
}