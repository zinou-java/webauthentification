package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "outils_carateristque")


public class OutilsCarateristque extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_outil")
    private Equipement idOutil;

    @ManyToOne
    @JoinColumn(name = "id_caraterstique")
    private Caracteristique idCaracteristique;
}
