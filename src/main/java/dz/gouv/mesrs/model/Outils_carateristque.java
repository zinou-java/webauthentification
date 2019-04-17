package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "outils_carateristque")


public class Outils_carateristque extends BaseEntity{

    @ManyToMany
    @JoinColumn(name = "id_outil")
    private Equipement idOUtil;

    @Column(name = "id_caraterstique")
    private String idCaract;
}
