package dz.gouv.mesrs.services.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.OutilsprojetDao;
import dz.gouv.mesrs.model.Outilsprojet;
import dz.gouv.mesrs.services.OutilsprojetService;
import org.springframework.beans.factory.annotation.Autowired;

public class OutilsprojetServicelmpl extends CommonServiceImpl<Outilsprojet, Integer>

implements OutilsprojetService {

    @Autowired
    OutilsprojetDao outilsprojetDao;

    @Override
    protected CommonDao<Outilsprojet, Integer> getDao(){return outilsprojetDao; }
}
