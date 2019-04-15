package dz.gouv.mesrs.dao.impl;


import dz.gouv.mesrs.dao.CarateristqueDao;
import dz.gouv.mesrs.model.Caracteristique;
import org.springframework.stereotype.Repository;

@Repository("caracteristiqueDaoImpl")

public class CaracteristiqueDaoImpl extends CommonDaoImpl<Caracteristique, Integer> implements
        CarateristqueDao {

    @Override
    protected Class<Caracteristique> getDomainClass () { return Caracteristique.class; }
}




