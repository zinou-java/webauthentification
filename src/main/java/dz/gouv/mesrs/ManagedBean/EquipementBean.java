package dz.gouv.mesrs.ManagedBean;


import dz.gouv.mesrs.model.Equipement;
import dz.gouv.mesrs.model.Outils_carateristque;
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

public class EquipementBean extends  BaseBean{


    List<Equipement> equipements ;

    private Equipement equipement;
    private Outils_carateristque outils_carateristque;

    @PostConstruct
    public void init() {
        equipement = new Equipement();
        outils_carateristque = new Outils_carateristque();

    }

    @PostConstruct
     public void getAll(){
        equipements = equipementService.findAll();


    }

    public void add(){

        equipement = equipementService.save(equipement);
          outils_carateristque.setIdOUtil(equipement);

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
