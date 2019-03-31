package dz.gouv.mesrs.ManagedBean;



import dz.gouv.mesrs.services.EquipementService;
import dz.gouv.mesrs.services.nc.NomenclatureService;
import dz.gouv.mesrs.services.ppm.RefEtablissementService;
import dz.gouv.mesrs.services.ppm.RefTypePieceConstitutiveService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Setter
@Getter
public abstract class BaseBean {

    public int step;

    @Autowired
    EquipementService equipementService;

    @Autowired
    RefEtablissementService refEtablissementService;

    @Autowired
    NomenclatureService nomenclatureService;


    @Autowired
    RefTypePieceConstitutiveService refTypePieceConstitutiveService;

    public abstract void init();

}

