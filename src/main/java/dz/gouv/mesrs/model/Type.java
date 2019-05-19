package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "type")

public class Type extends BaseEntity{

    @Column (name = "nom")
    private String nom;
}
