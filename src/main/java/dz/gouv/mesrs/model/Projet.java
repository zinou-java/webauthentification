package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table (name = "projet")

public class Projet extends BaseEntity{

    @Column(name = "annee_de_notification")
    private String annDnotification;

    @Column(name = "montant")
    private String Montant;


    @ManyToOne
    @JoinColumn (name = "conf_regionale")
    private Liste confregionale;


    @ManyToOne
    @JoinColumn (name = "etablissement")
    private Liste idetablissement;

    @Column (name = "credit_budgetaire_alloue")
    private String credit;
}

