package com.dlsys.sifood.dts.service.impl;

import com.dlsys.sifood.dts.dao.IDinningTableDao;
import com.dlsys.sifood.dts.dao.ITableGroupDao;
import com.dlsys.sifood.dts.dto.DinnigResponse;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.entity.DinningTable;
import com.dlsys.sifood.dts.entity.TableGroup;
import com.dlsys.sifood.dts.model.DinningModel;
import com.dlsys.sifood.dts.response.EntityResponse;
import com.dlsys.sifood.dts.response.ListResponse;
import com.dlsys.sifood.dts.service.IDinningTableService;
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


@Service
public class DinningTableService implements IDinningTableService {

    @Autowired
    private IDinningTableDao dinningDao;

    @Autowired
    private ITableGroupDao tableDao;

    @Override
    public ResponseEntity<?> postDinningTable(DinningTable dinning, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try{
            dinningDao.save(dinning);
            TableGroup table = new TableGroup(null, 1);
            table.setDinning(dinning);
            tableDao.save(table);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullDinningTable(dinning);
    }

    @Override
    public ResponseEntity<?> putDinningTable(DinningTable dinning, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try{
            dinningDao.save(dinning);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullDinningTable(dinning);
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
        if(dinning.isEmpty()){
            if (dinning.isEmpty()) {
                return EntityResponse.getNotFoundMessage();
            }
        }
        return EntityResponse.getSuccessfullListDinningTable(dinning);
    }
}
