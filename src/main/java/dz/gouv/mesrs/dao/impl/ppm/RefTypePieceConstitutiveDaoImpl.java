package dz.gouv.mesrs.dao.impl.ppm;

import dz.gouv.mesrs.dao.impl.CommonDaoImpl;
import dz.gouv.mesrs.dao.ppm.RefTypePieceConstitutiveDao;
import dz.gouv.mesrs.model.ppm.RefTypePieceConstitutive;
import org.springframework.stereotype.Repository;

/**
 * Created by USER on 06/06/2018.
 */
@Repository("refTypePieceConstitutiveDao")
public class RefTypePieceConstitutiveDaoImpl extends CommonDaoImpl<RefTypePieceConstitutive, Integer> implements
        RefTypePieceConstitutiveDao {

    @Override
    protected Class<RefTypePieceConstitutive> getDomainClass() {
        return RefTypePieceConstitutive.class;
    }
}
