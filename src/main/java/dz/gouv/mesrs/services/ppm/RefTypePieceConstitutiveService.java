package dz.gouv.mesrs.services.ppm;

import dz.gouv.mesrs.model.nc.Nomenclature;
import dz.gouv.mesrs.model.ppm.RefTypePieceConstitutive;
import dz.gouv.mesrs.services.CommonService;

import java.util.List;

public interface RefTypePieceConstitutiveService   extends CommonService<RefTypePieceConstitutive, Integer> {
public List<RefTypePieceConstitutive> getRefTypePieceConstitutivByNcTypePience(String ncTypePience);
}
