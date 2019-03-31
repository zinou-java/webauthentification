package dz.gouv.mesrs.dao.impl.nc;

import dz.gouv.mesrs.dao.impl.CommonDaoImpl;
import dz.gouv.mesrs.dao.nc.NomenclatureDao;
import dz.gouv.mesrs.model.nc.Nomenclature;
import org.springframework.stereotype.Repository;

@Repository("nomenclatureDao")
public class NomenclatureDaoImpl extends CommonDaoImpl<Nomenclature, Integer> implements
        NomenclatureDao {

    @Override
    protected Class<Nomenclature> getDomainClass() {
        return Nomenclature.class;
    }
}
