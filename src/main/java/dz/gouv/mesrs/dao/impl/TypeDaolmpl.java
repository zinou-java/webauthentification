package dz.gouv.mesrs.dao.impl;

import dz.gouv.mesrs.dao.TypeDao;
import dz.gouv.mesrs.model.Type;
import org.springframework.stereotype.Repository;



@Repository ("typeDaoImpl")

public class TypeDaolmpl extends CommonDaoImpl <Type, Integer>  implements
        TypeDao{

    @Override
    protected Class <Type> getDomainClass () { return Type.class; }
}
