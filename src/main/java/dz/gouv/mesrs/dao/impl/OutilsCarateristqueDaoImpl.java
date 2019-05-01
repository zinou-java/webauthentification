package dz.gouv.mesrs.dao.impl;

import dz.gouv.mesrs.dao.OutilsCarateristqueDao;
import dz.gouv.mesrs.model.OutilsCaracteristique;
import org.springframework.stereotype.Repository;


@Repository("outilsCarateristqueDaoImpl")
public class OutilsCarateristqueDaoImpl extends CommonDaoImpl<OutilsCaracteristique, Integer> implements
        OutilsCarateristqueDao{

    @Override
    protected Class<OutilsCaracteristique> getDomainClass () { return OutilsCaracteristique.class; }
}
