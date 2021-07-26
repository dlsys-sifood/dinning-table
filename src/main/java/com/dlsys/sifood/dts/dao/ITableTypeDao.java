package com.dlsys.sifood.dts.dao;

import com.dlsys.sifood.dts.entity.TableType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ITableTypeDao extends CrudRepository<TableType, UUID>, JpaSpecificationExecutor<TableType> {
}
