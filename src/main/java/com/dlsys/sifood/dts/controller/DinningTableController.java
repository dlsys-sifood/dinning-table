package com.dlsys.sifood.dts.controller;

import com.dlsys.sifood.dts.entity.DinningTable;
import com.dlsys.sifood.dts.model.DinningModel;
import com.dlsys.sifood.dts.service.dinningTable.IDinningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dinning")
public class DinningTableController {


    @Autowired
    IDinningTableService dinningService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> postDinningTable(@Valid @RequestBody DinningTable dinning, BindingResult result) {
        return dinningService.postDinningTable(dinning, result);
    }
    @RequestMapping(value = "/getInformationDinning", method = RequestMethod.GET)
    public ResponseEntity<?> getDinningTable(@RequestBody DinningModel dinning) {
        return dinningService.getDinningTable(dinning);
    }
    @RequestMapping(value = "/puInformationDinning", method = RequestMethod.PUT)
    public ResponseEntity<?> putDinningTable(@Valid @RequestBody DinningTable dinning, BindingResult result) {
        return dinningService.putDinningTable(dinning, result);
    }


}
