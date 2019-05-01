package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "outils_caracteristique")


public class OutilsCaracteristique extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_outil")
    private Equipement idOutil;

    @ManyToOne
    @JoinColumn(name = "id_caraterstique")
    private Caracteristique idCaracteristique;
}
