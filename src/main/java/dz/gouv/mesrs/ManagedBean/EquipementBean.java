package dz.gouv.mesrs.ManagedBean;


import dz.gouv.mesrs.model.*;
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
    List<Projet> projets;
    List<Caracteristique> caracteristiques;
    List<Liste> listes;
    private Boolean shownew;


    private Projet projet;
    private Equipement equipement;
    private OutilsCaracteristique outilsCaracteristique;
    private Liste liste;
    private Integer idCaracteristique;
    private Integer idEquipement;
    private Integer idProjet;
    private Integer etablissementListe;
    private Integer confregionaleListe;

    @PostConstruct
    public void init() {
        equipement = new Equipement();
        outilsCaracteristique = new OutilsCaracteristique();
        projet = new Projet();
        liste = new Liste();
        shownew = false;
    }

    @PostConstruct
    public void getAll() {
        equipements = equipementService.findAll();
        caracteristiques = carateristqueService.findAll();
        projets = projetService.findAll();
        listes = listeService.findAll();

    }


    public void addEquipementAndOutils(){

        Equipement e = getEquipementService().findById(idEquipement);
        Caracteristique c =getCarateristqueService().findById(idCaracteristique);
        outilsCaracteristique.setIdOutil(e);
        outilsCaracteristique.setIdCaracteristique(c);
        outilCarateristqueService.save(outilsCaracteristique);

    }



    public void add() {

        equipementService.save(equipement);
        equipement = new Equipement();
        equipements = equipementService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est enregistré", null));

    }

    public void addProjet () {

        Liste l = getListeService().findById(etablissementListe);
        Liste r = getListeService().findById(confregionaleListe);
        projet.setIdetablissement(l);
        projet.setConfregionale(r);
        projetService.save(projet);
        projet = new  Projet();
        projets = projetService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est enregistré", null));
        }

    public void removeProjet(Projet pro) {
        projetService.remove(pro);
        projets = projetService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est supprimé", null));
    }



    public void remove(Equipement eqp) {
        equipementService.remove(eqp);
        equipements = equipementService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "l'equipempent est supprimé", null));

    }
            public  void show(){
            shownew = true;
}

}
