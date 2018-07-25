import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJS {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.put("a", "1");
        engine.put("b", "2");
        Boolean conditionCheck = (Boolean) engine.eval("a==1 || b ==1");
        System.out.println("conditionCheck = " + conditionCheck);

        engine.eval("var obj1= {'result':parseInt(a)+2}");
        String obj = (String) engine.eval("JSON.stringify(obj1)");
        System.out.println("obj = " + obj);
    }
}
