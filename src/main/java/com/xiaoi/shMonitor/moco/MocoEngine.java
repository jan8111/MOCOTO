package com.xiaoi.shMonitor.moco;

import com.xiaoi.shMonitor.entity.SimReq;

import javax.script.*;
import java.util.Map;

public class MocoEngine {
    String execute(Iterable<Map.Entry<String, String>> params, SimReq simreq) throws ScriptException {
        if (simreq.getConditions() != null && simreq.getResults() != null) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            for (Map.Entry<String, String> param : params) {
                engine.put(param.getKey(), param.getValue());
            }

            for (int i = 0; i < simreq.getConditions().length; i++) {
                String condition1 = simreq.getConditions()[i];
                String result1 = simreq.getResults()[i];
                Boolean conditionCheck = (Boolean) engine.eval(condition1);
                if (conditionCheck) {
                    engine.eval("var obj1= "+result1);
                    return (String) engine.eval("JSON.stringify(obj1)");
                }
            }
        }
        return null;
    }
}
