package dz.gouv.mesrs.dao.impl;


import dz.gouv.mesrs.dao.EquipementDao;
import dz.gouv.mesrs.model.Equipement;
import org.springframework.stereotype.Repository;


@Repository("equipementDaoImpl")
public class EquipementDaoImpl extends CommonDaoImpl<Equipement, Integer> implements
        EquipementDao {

    @Override
    protected Class<Equipement> getDomainClass() {
        return Equipement.class;
    }
}