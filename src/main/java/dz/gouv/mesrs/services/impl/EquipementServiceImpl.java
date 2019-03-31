package dz.gouv.mesrs.services.impl;


import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.EquipementDao;


import dz.gouv.mesrs.model.Equipement;

import dz.gouv.mesrs.services.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("equipementService")
public class EquipementServiceImpl extends CommonServiceImpl<Equipement, Integer>
        implements EquipementService {
    @Autowired
    EquipementDao equipementDao;

    @Override
    protected CommonDao<Equipement, Integer> getDao() {
        return equipementDao;
    }
}
