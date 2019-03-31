package dz.gouv.mesrs.services.impl.nc;

import dz.gouv.mesrs.dao.CommonDao;
import dz.gouv.mesrs.dao.SearchMode;
import dz.gouv.mesrs.dao.nc.NomenclatureDao;
import dz.gouv.mesrs.model.nc.Nomenclature;
import dz.gouv.mesrs.services.impl.CommonServiceImpl;
import dz.gouv.mesrs.services.nc.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service("nomenclatureService")
public class NomenclatureServiceImpl extends CommonServiceImpl<Nomenclature, Integer>
        implements NomenclatureService {
    @Autowired
    NomenclatureDao nomenclatureDao;

    @Override
    public List<SelectItem> findNomenclatureList(String name) {
        List<SelectItem> selectItems = new ArrayList<>();
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setStatus(true);
        SearchMode searchMode = new SearchMode();
        searchMode.addFilter(new SearchMode.Filter("ncNames.nomNomenclature", name, SearchMode.FilterMode.EQUALS));
        List<Nomenclature> ncs = nomenclatureDao.findAllByExample(nomenclature, searchMode);

        if (ncs != null && !ncs.isEmpty()) {

            ncs.stream()
                    .forEach(e -> selectItems.add(new SelectItem(e.getId(), e.getLibelleLongFr())));
            return selectItems;
        } else
            return null;
    }



    @Override
    public List<SelectItem> findNomenclatureListAr(String name) {
        List<SelectItem> selectItems = new ArrayList<>();
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setStatus(true);
        SearchMode searchMode = new SearchMode();
        searchMode.addFilter(new SearchMode.Filter("ncNames.nomNomenclature", name, SearchMode.FilterMode.EQUALS));
        List<Nomenclature> ncs = nomenclatureDao.findAllByExample(nomenclature, searchMode);

        if (ncs != null && !ncs.isEmpty()) {

            ncs.stream()
                    .forEach(e -> selectItems.add(new SelectItem(e.getId(), e.getLibelleLongAr())));
            return selectItems;
        } else
            return null;
    }




    @Override
    public List<SelectItem> findNomenclatureListFr(String name) {
        List<SelectItem> selectItems = new ArrayList<>();
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setStatus(true);
        SearchMode searchMode = new SearchMode();
        searchMode.addFilter(new SearchMode.Filter("ncNames.nomNomenclature", name, SearchMode.FilterMode.EQUALS));
        List<Nomenclature> ncs = nomenclatureDao.findAllByExample(nomenclature, searchMode);


        if (ncs != null && !ncs.isEmpty()) {
            ncs.sort(Comparator.comparing(Nomenclature::getLibelleLongFr));
            ncs.stream()
                    .forEach(e -> selectItems.add(new SelectItem(e.getId(), e.getLibelleLongFr())));
            return selectItems;
        } else
            return null;
    }



    @Override

    public List<SelectItem> findNomenclatureAnneeList(String name, String anneDebut, String anneeFin) {
        List<SelectItem> selectItems = new ArrayList<>();
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setStatus(true);
        SearchMode searchMode = new SearchMode();
        searchMode.addFilter(new SearchMode.Filter("ncNames.nomNomenclature", name, SearchMode.FilterMode.EQUALS));
        searchMode.addFilter(new SearchMode.Filter("code", anneDebut, anneeFin, SearchMode.FilterMode.BETWEEN));
        searchMode.addSortField(new SearchMode.SortField("code", SearchMode.SortOrder.DESC));
        List<Nomenclature> ncs = nomenclatureDao.findAllByExample(nomenclature, searchMode);
        if (ncs != null && !ncs.isEmpty()) {
            ncs.stream()
                    .forEach(e -> selectItems.add(new SelectItem(e.getCode(), e.getLibelleLongFr())));
            return selectItems;
        } else
            return null;
    }

    @Override
    protected CommonDao<Nomenclature, Integer> getDao() {
        return nomenclatureDao;
    }
}
