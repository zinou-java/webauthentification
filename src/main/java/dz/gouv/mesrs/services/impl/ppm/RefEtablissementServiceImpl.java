package dz.gouv.mesrs.services.impl.ppm;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.ppm.RefEtablissementDao;
import dz.gouv.mesrs.model.ppm.RefEtablissement;
import dz.gouv.mesrs.services.impl.CommonServiceImpl;
import dz.gouv.mesrs.services.ppm.RefEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@Service("refEtablissementService")
public class RefEtablissementServiceImpl extends CommonServiceImpl<RefEtablissement, Integer>
        implements RefEtablissementService {
    @Autowired
    RefEtablissementDao refEtablissementDao;


@Override
   public List<SelectItem> getListEtablissement(){
        List<SelectItem> selectItems=new ArrayList<>();
        RefEtablissement refEtablissement = new RefEtablissement();
        refEtablissement.setEtatblissementEnseignementSuperieur(true);
        List<RefEtablissement> allByExample = refEtablissementDao.findAllByExample(refEtablissement);
        if(allByExample!=null && !allByExample.isEmpty()) {
        allByExample.stream().forEach(e->selectItems.add(new SelectItem(e.getId(),e.getLlEtablissementLatin())));
        }
        return selectItems;
    }

    @Override
    public List<SelectItem> getListEtablissementAr(){
        List<SelectItem> selectItems=new ArrayList<>();
        RefEtablissement refEtablissement = new RefEtablissement();
        refEtablissement.setEtatblissementEnseignementSuperieur(true);
        List<RefEtablissement> allByExample = refEtablissementDao.findAllByExample(refEtablissement);
        if(allByExample!=null && !allByExample.isEmpty()) {
            allByExample.stream().forEach(e->selectItems.add(new SelectItem(e.getId(),e.getLlEtablissementArabe())));
        }
        return selectItems;
    }
    @Override
    protected CommonDao<RefEtablissement, Integer> getDao() {
        return refEtablissementDao;
    }
}
