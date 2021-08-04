package com.dlsys.sifood.dts.dao;

import com.dlsys.sifood.dts.entity.TableType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITableTypeDao extends CrudRepository<TableType, UUID>, JpaSpecificationExecutor<TableType> {
}
