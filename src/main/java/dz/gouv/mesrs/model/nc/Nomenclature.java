package dz.gouv.mesrs.model.nc;

import dz.gouv.mesrs.model.BaseEntity;

import javax.persistence.*;
 
@Entity
@Table(name = "nomenclature", schema = "nc")
public class Nomenclature extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "code", unique = false, nullable = false, length = 30)
	private String code;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_list")
	private NcNames ncNames;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ref_valeur")
	private Nomenclature nomenclature;
	@Column(name = "libelle_long_fr", length = 100)
	private String libelleLongFr;
	@Column(name = "libelle_long_ar", length = 100)
	private String libelleLongAr;
	@Column(name = "libelle_court_fr", length = 100)
	private String libelleCourtFr;
	@Column(name = "libelle_court_ar", length = 100)
	private String libelleCourtAr;

	@Column(name = "status")
	private Boolean status;
	@Column(name = "rang")
	private Integer rang;

	public Nomenclature() {
	}





	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}


	public NcNames getNcNames() {
		return this.ncNames;
	}

	public void setNcNames(NcNames ncNames) {
		this.ncNames = ncNames;
	}


	public String getLibelleLongFr() {
		return this.libelleLongFr;
	}

	public void setLibelleLongFr(String libelleLongFr) {
		this.libelleLongFr = libelleLongFr;
	}


	public String getLibelleLongAr() {
		return this.libelleLongAr;
	}

	public void setLibelleLongAr(String libelleLongAr) {
		this.libelleLongAr = libelleLongAr;
	}


	public String getLibelleCourtFr() {
		return this.libelleCourtFr;
	}

	public void setLibelleCourtFr(String libelleCourtFr) {
		this.libelleCourtFr = libelleCourtFr;
	}


	public String getLibelleCourtAr() {
		return this.libelleCourtAr;
	}

	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	


	public Integer getRang() {
		return rang;
	}

	/**
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}




	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
