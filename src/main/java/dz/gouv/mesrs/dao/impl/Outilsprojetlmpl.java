package dz.gouv.mesrs.dao.impl;


import dz.gouv.mesrs.dao.OutilsprojetDao;
import dz.gouv.mesrs.model.Outilsprojet;
import org.springframework.stereotype.Repository;

@Repository("Outilsprojetlmpl")

public class Outilsprojetlmpl extends CommonDaoImpl<Outilsprojet, Integer>
        implements OutilsprojetDao {

           @Override

    protected Class <Outilsprojet> getDomainClass () { return Outilsprojet.class; }

}
