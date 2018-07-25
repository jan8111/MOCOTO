package com.xiaoi.shMonitor.service;

import com.xiaoi.shMonitor.cache.SimReqCache;
import com.xiaoi.shMonitor.entity.SimReq;
import com.xiaoi.shMonitor.repo.SimReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SimReqService {

    @Autowired
    SimReqRepository repository;

    @Autowired
    SimReqCache simReqCache;


    public Page<SimReq> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public SimReq save(SimReq bean) {
        if(bean.getMethod()!=null){
            bean.setMethod(bean.getMethod().toUpperCase());
        }
        SimReq en = repository.save(bean);
        if(en!=null){
            simReqCache.remove(en.getPath(),en.getMethod());
        }
        return en;
    }

    public SimReq del(String id) {
        repository.delete(id);
        SimReq en1 = repository.findOne(id);
        if(en1!=null){
            return simReqCache.remove(en1.getPath(),en1.getMethod());
        }else {
            return null;
        }

    }

    public SimReq findOne(String id) {
        return repository.findOne(id);
    }
}
