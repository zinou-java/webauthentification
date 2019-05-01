package dz.gouv.mesrs.services.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.ProjetDao;

import dz.gouv.mesrs.model.Projet;

import dz.gouv.mesrs.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service ("projetService")
public class ProjetServiceImpl extends CommonServiceImpl <Projet, Integer>
        implements ProjetService {

    @Autowired
    ProjetDao projetDao;

    @Override
    protected CommonDao<Projet, Integer> getDao () {return projetDao;}
}
