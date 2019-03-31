package dz.gouv.mesrs.ManagedBean;


import dz.gouv.mesrs.model.Equipement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.List;

@Setter
@Getter
@Data
@Named("equipementBean")
@Scope("request")

public class EquipementBean extends  BaseBean{


    List<Equipement> equipements ;

    private Equipement equipement;

    @Override
    @PostConstruct
    public void init() {
        equipement = new Equipement();
    }

    @PostConstruct

    public void getAll(){
        equipements = equipementService.findAll();

    }

    public void add(){
           equipementService.save(equipement);
    }


    public void remove(Equipement eqp) {
                equipementService.remove(eqp);

    }

}
