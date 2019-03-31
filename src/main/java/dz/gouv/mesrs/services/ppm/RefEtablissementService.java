package dz.gouv.mesrs.services.ppm;

import dz.gouv.mesrs.model.ppm.RefEtablissement;
import dz.gouv.mesrs.services.CommonService;

import javax.faces.model.SelectItem;
import java.util.List;

public interface RefEtablissementService extends CommonService<RefEtablissement, Integer> {

    List<SelectItem> getListEtablissement();
    List<SelectItem> getListEtablissementAr();
}
