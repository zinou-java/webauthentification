package dz.gouv.mesrs.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "outils")
public class Equipement extends BaseEntity {

    @Column(name = "label")
    private String labelEquipement;

}