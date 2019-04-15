package dz.gouv.mesrs.dao.impl;

import dz.gouv.mesrs.dao.OutilsCarateristqueDao;
import dz.gouv.mesrs.model.OutilsCarateristque;
import org.springframework.stereotype.Repository;


@Repository("outilsCarateristqueDaoImpl")
public class OutilsCarateristqueDaoImpl extends CommonDaoImpl<OutilsCarateristque, Integer> implements
        OutilsCarateristqueDao{

    @Override
    protected Class<OutilsCarateristque> getDomainClass () { return OutilsCarateristque.class; }
}
