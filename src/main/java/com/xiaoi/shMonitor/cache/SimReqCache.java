package com.xiaoi.shMonitor.cache;

import com.xiaoi.shMonitor.entity.SimReq;
import com.xiaoi.shMonitor.repo.SimReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimReqCache {

    @Autowired
    SimReqRepository simReqRepository;

    private Map<String,SimReq> map = new ConcurrentHashMap<>();


    public SimReq getSimReq(String path,String method){
        return map.computeIfAbsent(makeKey(path,method),(key1)->findFromDb(path,method));
    }

    private String makeKey(String path, String method) {
        return path+"#"+method;
    }

    private SimReq findFromDb(String path,String method) {
        return simReqRepository.findByPathAndMethod(path,method);
    }

    public SimReq remove(String path,String method){
        return map.remove(makeKey(path,method));
    }


}
