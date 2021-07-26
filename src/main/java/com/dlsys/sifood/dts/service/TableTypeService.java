package com.dlsys.sifood.dts.service;

import com.dlsys.sifood.dts.dao.ITableTypeDao;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.dto.TableTypeResponse;
import com.dlsys.sifood.dts.entity.TableType;
import com.dlsys.sifood.dts.model.TableTypeModel;
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
public class TableTypeService implements ITableTypeService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    ITableTypeDao tableDao;

    @Override
    public ResponseEntity<?> postTableType(TableType table, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try{
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseTableType(new TableTypeResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso en el tipo de tablo"), table))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putPorfile(TableType table, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try{
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseTableType(new TableTypeResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("actualizado exitoso en el tipo de tablo"), table))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProfile(TableTypeModel table) {
        List<TableType> response = new ArrayList<>();

            try {
                response = tableDao.findAll(new Specification<TableType>() {
                    @Override
                    public Predicate toPredicate(Root<TableType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                        Predicate p = cb.conjunction();
                        if(!table.getSeccion().isEmpty()) {
                            p = cb.and(p, cb.equal(root.get("seccion"),  table.getSeccion()));
                        }
                        if(!table.getName().isEmpty()) {
                            p = cb.and(p, cb.like(root.get("name"), "%" + table.getName() + "%"));
                        }
                        if(!table.getId().isEmpty()) {
                            p = cb.and(p, cb.equal(root.get("id"), UUID.fromString(table.getId())));
                        }
                        return p;
                    }
                });
            } catch (RuntimeException e){
                throw new RuntimeException(e);
            }
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseTableType(new TableTypeResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("actualizado exitoso en el tipo de tablo"), response))
                , HttpStatus.OK);

    }
}
