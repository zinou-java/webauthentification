package dz.gouv.mesrs.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "listes")

public class Liste extends BaseEntity{

    @Column (name = "etablissement")
    private String etablissement;

    @Column (name = "region" )
    private  String region;


}
