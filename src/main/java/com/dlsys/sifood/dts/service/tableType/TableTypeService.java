package com.dlsys.sifood.dts.service.tableType;

import com.dlsys.sifood.dts.dao.ITableTypeDao;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.dto.TableTypeResponse;
import com.dlsys.sifood.dts.entity.TableType;
import com.dlsys.sifood.dts.model.TableTypeModel;
import com.dlsys.sifood.dts.service.GenericService;
import com.dlsys.sifood.dts.service.ServiceResponse;
import com.dlsys.sifood.dts.service.tableType.ITableTypeService;
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
import java.util.UUID;

@Service
public class TableTypeService implements ITableTypeService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    @Autowired
    ITableTypeDao tableDao;

    @Override
    public ResponseEntity<?> postTableType(TableType table, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try{
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullListTableType(table);
    }

    @Override
    public ResponseEntity<?> putPorfile(TableType table, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try{
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullListTableType(table);
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
            return  GenericService.getSuccessfullListTableType(response);
    }
}
