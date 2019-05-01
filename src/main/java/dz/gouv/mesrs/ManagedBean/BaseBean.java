package dz.gouv.mesrs.ManagedBean;



import dz.gouv.mesrs.services.*;
import dz.gouv.mesrs.services.impl.CaracteristiqueServiceImpl;
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
    CarateristqueService carateristqueService;

    @Autowired
    ProjetService projetService;

    @Autowired
    ListeService listeService;

    @Autowired
    RefEtablissementService refEtablissementService;

    @Autowired
    NomenclatureService nomenclatureService;

    @Autowired
    OutilCarateristqueService outilCarateristqueService;

    @Autowired
    RefTypePieceConstitutiveService refTypePieceConstitutiveService;

    public abstract void init();

}

