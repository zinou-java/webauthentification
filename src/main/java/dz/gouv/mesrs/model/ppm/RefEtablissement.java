package dz.gouv.mesrs.model.ppm;

// Generated 10 avr. 2014 10:28:25 by Hibernate Tools 3.6.0

import dz.gouv.mesrs.model.BaseEntity;
import dz.gouv.mesrs.model.nc.Nomenclature;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ref_etablissement", schema = "ppm")
public class RefEtablissement extends BaseEntity {


	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_forme_juridique_id")
	private Nomenclature nomenclatureByNcFormeJuridiqueId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_statut_etab_id")
	private Nomenclature nomenclatureByNcStatutEtabId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_etablissement")
	private Nomenclature ncTypeEtablissement;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wilaya")
	private Nomenclature ncWilaya;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_region")
	private Nomenclature region;
	@Column(name = "identifiant", unique = true, nullable = false, length = 6)
	private String identifiant;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation", nullable = false, length = 13)
	private Date dateCreation;
	@Column(name = "lc_etablissement_arabe", nullable = false, length = 250)
	private String lcEtablissementArabe;
	@Column(name = "lc_etablissement_latin", nullable = false, length = 250)
	private String lcEtablissementLatin;
	@Column(name = "ll_etablissement_arabe", nullable = false, length = 250)
	private String llEtablissementArabe;
	@Column(name = "ll_etablissement_latin", nullable = false, length = 250)
	private String llEtablissementLatin;
	@Column(name = "nif", length = 250)
	private String nif;
	@Column(name = "nis", length = 250)
	private String nis;
	@Column(name = "nrc", length = 250)
	private String nrc;
	@Column(name = "nss", length = 250)
	private String nss;
	@Column(name = "decret", length = 250)
	private String decret;
	@Column(name = "ancien_code")
	private String ancienCode;
	@Column(name = "active", columnDefinition = "boolean default true")
	private Boolean active = true;

	@Column(name="latitude")
	private Double latitude;
	@Column(name="longitude")
	private Double longitude;
	private byte[] logo;

	@Column(name="etablissement_enseignement_suprieur")
	private Boolean etatblissementEnseignementSuperieur;
	
	public RefEtablissement() {
	}





	public Nomenclature getNomenclatureByNcFormeJuridiqueId() {
		return this.nomenclatureByNcFormeJuridiqueId;
	}

	public void setNomenclatureByNcFormeJuridiqueId(Nomenclature nomenclatureByNcFormeJuridiqueId) {
		this.nomenclatureByNcFormeJuridiqueId = nomenclatureByNcFormeJuridiqueId;
	}


	public Nomenclature getNcTypeEtablissement() {
		return this.ncTypeEtablissement;
	}

	public void setNcTypeEtablissement(Nomenclature ncTypeEtablissement) {
		this.ncTypeEtablissement = ncTypeEtablissement;
	}


	public Nomenclature getNcWilaya() {
		return this.ncWilaya;
	}

	public void setNcWilaya(Nomenclature ncWilaya) {
		this.ncWilaya = ncWilaya;
	}
	

	public Nomenclature getRegion() {
		return region;
	}

	public void setRegion(Nomenclature region) {
		this.region = region;
	}


	public Nomenclature getNomenclatureByNcStatutEtabId() {
		return this.nomenclatureByNcStatutEtabId;
	}

	public void setNomenclatureByNcStatutEtabId(Nomenclature nomenclatureByNcStatutEtabId) {
		this.nomenclatureByNcStatutEtabId = nomenclatureByNcStatutEtabId;
	}


	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getLcEtablissementArabe() {
		return this.lcEtablissementArabe;
	}

	public void setLcEtablissementArabe(String lcEtablissementArabe) {
		this.lcEtablissementArabe = lcEtablissementArabe;
	}


	public String getLcEtablissementLatin() {
		return this.lcEtablissementLatin;
	}

	public void setLcEtablissementLatin(String lcEtablissementLatin) {
		this.lcEtablissementLatin = lcEtablissementLatin;
	}


	public String getLlEtablissementArabe() {
		return this.llEtablissementArabe;
	}

	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	public String getLlEtablissementLatin() {
		return this.llEtablissementLatin;
	}

	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getDecret() {
		return this.decret;
	}

	public void setDecret(String decret) {
		this.decret = decret;
	}

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "refEtablissement")
	// public Set<RefHistorique> getRefHistoriques() {
	// return refHistoriques;
	// }
	//
	// public void setRefHistoriques(Set<RefHistorique> refHistoriques) {
	// this.refHistoriques = refHistoriques;
	// }

	public String getAncienCode() {
		return ancienCode;
	}

	public void setAncienCode(String ancienCode) {
		this.ancienCode = ancienCode;
	}


	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getEtatblissementEnseignementSuperieur() {
		return etatblissementEnseignementSuperieur;
	}

	public void setEtatblissementEnseignementSuperieur(Boolean etatblissementEnseignementSuperieur) {
		this.etatblissementEnseignementSuperieur = etatblissementEnseignementSuperieur;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


}
