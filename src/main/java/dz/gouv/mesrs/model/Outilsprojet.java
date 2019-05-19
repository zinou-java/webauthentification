package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Data
@Entity
@Table

public class Outilsprojet extends BaseEntity {

    @Column (name = "Qte")
    private String Quantite;

}
