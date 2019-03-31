package dz.gouv.mesrs.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity  implements Serializable, Identifiable<Integer>, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public Integer getIdenfiant() {
        return getId();
    }

    @Override
    public String getIdentifiantName() {
        return "id";
    }


}
