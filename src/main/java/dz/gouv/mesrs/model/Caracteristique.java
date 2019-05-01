package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "caracteristique")

public class Caracteristique extends BaseEntity {


    @Column (name = "nom")
    private String nomCaracteristique;
}
