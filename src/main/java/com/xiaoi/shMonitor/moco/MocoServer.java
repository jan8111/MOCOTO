package com.xiaoi.shMonitor.moco;


import com.xiaoi.shMonitor.cache.SimReqCache;
import com.xiaoi.shMonitor.entity.SimReq;
import com.xiaoi.shMonitor.util.BeanTools;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;

import javax.script.ScriptException;

public class MocoServer extends AbstractVerticle {

    private static final int port = 8081;

    @Override
    public void start() throws Exception {
        SimReqCache simReqCache = (SimReqCache) BeanTools.getBean(SimReqCache.class);

        vertx.createHttpServer().requestHandler(req -> {
            if("/favicon.ico".equals(req.path())){
                return;
            }

            MultiMap params = req.params();
            SimReq simreq = simReqCache.getSimReq(req.path(), req.method().toString());
            if(simreq!=null) {
                try {
                    String result1 = new MocoEngine().execute(params,simreq);
                    if(result1==null) result1="";
                    req.response().putHeader("content-type", "application/json").end(result1);
                } catch (ScriptException e) {
                    e.printStackTrace();
                    req.response().putHeader("content-type", "text/html").end("ScriptException "+e.getMessage());
                }
            }else{
                req.response().putHeader("content-type", "text/html").end("no find: "+req.path()+" "+req.method());
            }
        }).listen(port);
        System.out.println("MocoServer port = " + port);
    }
}