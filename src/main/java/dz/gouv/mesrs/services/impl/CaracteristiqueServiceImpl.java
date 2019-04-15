package dz.gouv.mesrs.services.impl;


import dz.gouv.mesrs.dao.CarateristqueDao;
import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.impl.CaracteristiqueDaoImpl;
import dz.gouv.mesrs.model.Caracteristique;
import dz.gouv.mesrs.services.CarateristqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("carateristqueService")

public class CaracteristiqueServiceImpl extends CommonServiceImpl<Caracteristique, Integer>
        implements CarateristqueService {

    @Autowired
    CarateristqueDao carateristqueDao;

    @Override
    protected CommonDao<Caracteristique, Integer> getDao ()  { return carateristqueDao; }

}
