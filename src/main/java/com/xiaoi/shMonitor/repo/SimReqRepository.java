package com.xiaoi.shMonitor.repo;

import com.xiaoi.shMonitor.entity.SimReq;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SimReqRepository extends MongoRepository<SimReq,String> {
    public SimReq findByPathAndMethod(String path,String method);


}
