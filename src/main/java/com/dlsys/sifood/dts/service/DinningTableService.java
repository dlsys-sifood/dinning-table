package com.dlsys.sifood.dts.service;

import com.dlsys.sifood.dts.dao.IDinningTableDao;
import com.dlsys.sifood.dts.dao.ITableGroupDao;
import com.dlsys.sifood.dts.dto.DinnigResponse;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.entity.DinningTable;
import com.dlsys.sifood.dts.entity.TableGroup;
import com.dlsys.sifood.dts.model.DinningModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DinningTableService implements IDinningTableService{


    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IDinningTableDao dinningDao;

    @Autowired
    ITableGroupDao tableDao;

    @Override
    public ResponseEntity<?> postDinningTable(DinningTable dinning, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try{
            dinningDao.save(dinning);
            TableGroup table = new TableGroup(null, 1);
            table.setDinning(dinning);
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseDinningTable(new DinnigResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso en el tipo de tablo"), dinning))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putDinningTable(DinningTable dinning, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try{
            dinningDao.save(dinning);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseDinningTable(new DinnigResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso en el tipo de tablo"), dinning))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDinningTable(DinningModel table) {
        List<DinningTable> dinning = new ArrayList<>();

        try {
            dinning = dinningDao.findAll(new Specification<DinningTable>() {
                @Override
                public Predicate toPredicate(Root<DinningTable> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if (!table.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + table.getName() + "%"));
                    }
                    if (!table.getReservation().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("reservation"), Integer.parseInt(table.getReservation())));
                    }
                    if (!table.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), UUID.fromString(table.getId())));
                    }
                    if (!table.getIdTableType().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id_table_type"), UUID.fromString(table.getIdTableType())));
                    }
                    if (!table.getUserCapacity().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("user_capacity"), Integer.parseInt(table.getUserCapacity())));
                    }
                    return p;
                }
            });
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(ServiceResponse
                .responseDinningTable(new DinnigResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), dinning)), HttpStatus.OK);
    }
}
