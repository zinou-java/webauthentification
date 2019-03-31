package dz.gouv.mesrs.services.nc;


import dz.gouv.mesrs.model.nc.Nomenclature;
import dz.gouv.mesrs.services.CommonService;

import javax.faces.model.SelectItem;
import java.util.List;

public interface NomenclatureService  extends CommonService<Nomenclature, Integer> {

    public List<SelectItem> findNomenclatureList(String name);
    public List<SelectItem> findNomenclatureListAr(String name);
    public List<SelectItem> findNomenclatureListFr(String name);
    public List<SelectItem> findNomenclatureAnneeList(String name, String anneDebut,String anneeFin);

}
