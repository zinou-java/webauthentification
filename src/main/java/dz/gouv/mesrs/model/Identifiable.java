package dz.gouv.mesrs.model;

import java.io.Serializable;

public interface Identifiable<T extends Serializable>{

	T getIdenfiant();
	
	String getIdentifiantName();
}
