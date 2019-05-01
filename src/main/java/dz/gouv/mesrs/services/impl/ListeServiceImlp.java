package dz.gouv.mesrs.services.impl;


import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.ListeDao;
import dz.gouv.mesrs.model.Liste;
import dz.gouv.mesrs.services.ListeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("ListeService")

public class ListeServiceImlp extends CommonServiceImpl <Liste, Integer>
        implements ListeService {

    @Autowired
    ListeDao listeDao;

    @Override
    protected CommonDao <Liste, Integer> getDao() {return listeDao;}



}
