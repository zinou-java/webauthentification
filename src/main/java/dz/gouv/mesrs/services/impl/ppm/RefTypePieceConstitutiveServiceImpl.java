package dz.gouv.mesrs.services.impl.ppm;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.SearchMode;
import dz.gouv.mesrs.dao.ppm.RefTypePieceConstitutiveDao;
import dz.gouv.mesrs.model.nc.Nomenclature;
import dz.gouv.mesrs.model.ppm.RefTypePieceConstitutive;
import dz.gouv.mesrs.services.impl.CommonServiceImpl;
import dz.gouv.mesrs.services.ppm.RefTypePieceConstitutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create('d by USER on 06/06/2018.
 */
@Service("refTypePieceConstitutiveService")
public class RefTypePieceConstitutiveServiceImpl extends CommonServiceImpl<RefTypePieceConstitutive, Integer>
        implements RefTypePieceConstitutiveService {

    @Autowired
    RefTypePieceConstitutiveDao refTypePieceConstitutiveDao;

    @Override
    protected CommonDao<RefTypePieceConstitutive, Integer> getDao() {
        return refTypePieceConstitutiveDao;
    }

    @Override
    public List<RefTypePieceConstitutive> getRefTypePieceConstitutivByNcTypePience(String ncTypePience) {
        RefTypePieceConstitutive refTypePieceConstitutive = new RefTypePieceConstitutive();
        SearchMode searchMode = new SearchMode();
        searchMode.addFilter(new SearchMode.Filter("ncTypeDossier.code",ncTypePience, SearchMode.FilterMode.EQUALS));
      return   refTypePieceConstitutiveDao.findAllByExample(refTypePieceConstitutive,searchMode);
    }
}
