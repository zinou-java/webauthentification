package dz.gouv.mesrs.model.ppm;

// Generated 20 mai 2014 15:30:43 by Hibernate Tools 3.6.0

import dz.gouv.mesrs.model.BaseEntity;
import dz.gouv.mesrs.model.nc.Nomenclature;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ref_type_piece_constitutive", schema = "ppm", uniqueConstraints = @UniqueConstraint(columnNames = {
        "type_dossier", "type_piece"}))
public class RefTypePieceConstitutive extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_piece")
    private Nomenclature ncTypePiece;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_dossier")
    private Nomenclature ncTypeDossier;

    @Column(name = "rang")
    private Integer rang;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut", nullable = false, length = 13)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin", length = 13)
    private Date dateFin;

    @Column(name = "obligatoire")
    private Boolean obligatoire;

    @Column(name = "a_fournir")
    private Boolean aFournir;

    @Column(name = "nombre")
    private Integer nombre;
}
