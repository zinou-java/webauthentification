package dz.gouv.mesrs.dao.impl;


import dz.gouv.mesrs.dao.ProjetDao;
import dz.gouv.mesrs.model.Projet;
import org.springframework.stereotype.Repository;

@Repository ("ProjetDaoImpl")

public class ProjetDaoImpl extends CommonDaoImpl <Projet, Integer> implements
        ProjetDao {

    @Override
    protected Class <Projet> getDomainClass () { return Projet.class; }

}