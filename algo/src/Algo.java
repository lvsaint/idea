import java.util.HashMap;
import java.util.Map;

public class Algo {

    private static Map<String, Integer> baseAssignMap = new HashMap<>();
    private static Map<String, String> expMap = new HashMap<>();

    public static void main(String[] args) throws ScriptException {
        String expr = "";
        String[] splitted = expr.split(";");
        FormulaPart fp = FormulaPart.build(splitted[0], splitted[1], splitted[2]);

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
