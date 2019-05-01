package dz.gouv.mesrs.dao.impl;


import dz.gouv.mesrs.dao.ListeDao;
import dz.gouv.mesrs.model.Liste;
import org.springframework.stereotype.Repository;



@Repository ("ListeDaoImpl")

public class ListeDaoImpl extends CommonDaoImpl <Liste, Integer>  implements
        ListeDao  {

    @Override
    protected Class <Liste> getDomainClass () {return  Liste.class;}
}
