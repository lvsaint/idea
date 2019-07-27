import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;

/**
 * Created by LuChao on 8/13/16.
 */
public class Algo {

    private static Map<String, Integer> baseAssignMap = new HashMap<>();
    private static Map<String, String> expMap = new HashMap<>();

    public static void main(String[] args) throws ScriptException {
        String expr = "[1234]=[12]+[34]*{50},[12]=[1]+[2]/{2};[1]=10,[2]=20,[34]=50;[1234]";
        String[] splitted = expr.split(";");
        FormulaPart fp = FormulaPart.build(splitted[0], splitted[1], splitted[2]);
        // System.out.println(fp.getFormula());
        // System.out.println(fp.getAssignment());
        // System.out.println(fp.getCalc());

        for (String ass : fp.getAssignment()) {
            String[] kv = ass.split("=");
            baseAssignMap.put(kv[0], Integer.parseInt(kv[1]));
        }

        for (String fm : fp.getFormula()) {
            String[] kv = fm.split("=");
            expMap.put(kv[0], kv[1]);
        }
        System.out.println(getResult(fp.getCalc(), baseAssignMap, expMap));
    }

    private static int getResult(String calc, Map<String, Integer> baseAssignMap, Map<String, String> expMap)
            throws ScriptException {
        if (calc.contains("{")) {
            return Integer.parseInt(calc.replaceAll("\\{|\\}", ""));
        }
        if (baseAssignMap.containsKey(calc)) {
            return baseAssignMap.get(calc);
        }
        String rightExp = expMap.get(calc);
        String[] terms = rightExp.split("[-\\+\\*/]");
        for (String term : terms) {
            int termResult = getResult(term.trim(), baseAssignMap, expMap);
            rightExp = rightExp.replace(term, termResult + "");
        }
        return eval2(rightExp);
    }

    private static int eval2(String exp) {
        return StrEval.eval(exp);
    }
}