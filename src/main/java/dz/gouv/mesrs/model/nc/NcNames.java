package dz.gouv.mesrs.model.nc;

import dz.gouv.mesrs.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "nc_names", schema = "nc")
public class NcNames extends BaseEntity {

	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ref_etrangere")
	private NcNames ncNames;
	@Column(name = "nom_nomenclature", unique = true, nullable = false, length = 100)
	private String nomNomenclature;
	@Column(name = "description", length = 200)
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "default_value")
	private Nomenclature defaultValue;

	public NcNames() {
	}





	public NcNames getNcNames() {
		return this.ncNames;
	}

	public void setNcNames(NcNames ncNames) {
		this.ncNames = ncNames;
	}


	public Nomenclature getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Nomenclature defaultValue) {
		this.defaultValue = defaultValue;
	}


	public String getNomNomenclature() {
		return this.nomNomenclature;
	}

	public void setNomNomenclature(String nomNomenclature) {
		this.nomNomenclature = nomNomenclature;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}