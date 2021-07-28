package com.dlsys.sifood.dts.service.impl;

import com.dlsys.sifood.dts.entity.TableGroup;;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


public interface ITableGroupService {
    public ResponseEntity<?> postTableGroup(TableGroup table, BindingResult result);
    public ResponseEntity<?> putTableGroup(TableGroup table, BindingResult result);
    public ResponseEntity<?> getTableGroup(TableGroup table);
}
