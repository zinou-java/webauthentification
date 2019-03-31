package dz.gouv.mesrs.dao.impl.ppm;

import dz.gouv.mesrs.dao.impl.CommonDaoImpl;
import dz.gouv.mesrs.dao.ppm.RefEtablissementDao;
import dz.gouv.mesrs.model.ppm.RefEtablissement;
import org.springframework.stereotype.Repository;

@Repository("refEtablissementDao")
public class RefEtablissementDaoImpl  extends CommonDaoImpl<RefEtablissement, Integer> implements
        RefEtablissementDao {

    @Override
    protected Class<RefEtablissement> getDomainClass() {
        return RefEtablissement.class;
    }
}
