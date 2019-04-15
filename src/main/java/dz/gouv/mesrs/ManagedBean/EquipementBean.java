package dz.gouv.mesrs.ManagedBean;


import dz.gouv.mesrs.model.Caracteristique;
import dz.gouv.mesrs.model.Equipement;
import dz.gouv.mesrs.model.OutilsCarateristque;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;


@Data
@Named("equipementBean")
@Scope("session")
@ViewScoped

public class EquipementBean extends BaseBean {


    List<Equipement> equipements;

    private Equipement equipement;
    private OutilsCarateristque outilsCarateristque;
    private List<Caracteristique> caracteristiques;
    private Integer idCaracteristique;
    private Integer idEquipement;

    @PostConstruct
    public void init() {
        equipement = new Equipement();
        outilsCarateristque = new OutilsCarateristque();

    }

    @PostConstruct
    public void getAll() {
        equipements = equipementService.findAll();
        caracteristiques = carateristqueService.findAll();


    }


    public void addEquipementAndOutils(){

        Equipement e = getEquipementService().findById(idEquipement);
        Caracteristique c =getCarateristqueService().findById(idCaracteristique);
        outilsCarateristque.setIdOutil(e);
        outilsCarateristque.setIdCaracteristique(c);
        outilCarateristqueService.save(outilsCarateristque);




    }
    public void add() {

        equipementService.save(equipement);
        equipement = new Equipement();
        equipements = equipementService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est enregistré", null));

    }


    public void remove(Equipement eqp) {
        equipementService.remove(eqp);
        equipements = equipementService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est supprimé", null));

    }


}
