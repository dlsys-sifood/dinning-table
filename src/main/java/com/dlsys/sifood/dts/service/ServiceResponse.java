package com.dlsys.sifood.dts.service;

import com.dlsys.sifood.dts.dto.DinnigResponse;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.dto.TableTypeResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceResponse {


    public static Map<String, Object> responseGeneric(GenericResponse generic){
        Map<String, Object> response = new HashMap<>();
        response.put("response", generic);
        return response;
    }

    public static Map<String, Object> responseTableType(TableTypeResponse table){
        Map<String, Object> response = new HashMap<>();
        response.put("response", table);
        return response;
    }

    public static Map<String, Object> responseDinningTable(DinnigResponse dinning){
        Map<String, Object> response = new HashMap<>();
        response.put("response", dinning);
        return response;
    }


}
