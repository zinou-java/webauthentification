package dz.gouv.mesrs.services.impl;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.TypeDao;
import dz.gouv.mesrs.model.Projet;
import dz.gouv.mesrs.model.Type;
import dz.gouv.mesrs.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service ("typeService")

public class TypeServicelmpl extends CommonServiceImpl <Type, Integer>
implements TypeService{

    @Autowired
    TypeDao typeDao;

    @Override
    protected CommonDao<Type, Integer> getDao () {return typeDao;}
}
