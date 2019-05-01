package dz.gouv.mesrs.services.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.OutilsCarateristqueDao;
import dz.gouv.mesrs.model.OutilsCaracteristique;
import dz.gouv.mesrs.services.OutilCarateristqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service ("outilCarateristqueService")

public class OutilCarateristqueServiceImpl extends CommonServiceImpl<OutilsCaracteristique, Integer>
        implements OutilCarateristqueService {

    @Autowired
    OutilsCarateristqueDao outilsCarateristqueDao;

    @Override
    protected CommonDao<OutilsCaracteristique,Integer> getDao() { return outilsCarateristqueDao; }

}
