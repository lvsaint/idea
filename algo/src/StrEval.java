import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrEval {
    public static void main(String[] args) {
        String str = "1 + 6/3+2 *1";
        System.out.println(eval(str));
    }

    public static int eval(String exp) {
        String[] terms = exp.split("-|\\+");
        if (terms.length == 1 && (!exp.contains("*") && !exp.contains("/"))) {
            return Integer.parseInt(exp.trim());
        }
        int sum = 0;
        int beginPos = 0;
        for (int i = 0; i < terms.length; i++) {
            if (i == 0) {
                sum = evalTerm(terms[i]);
                continue;
            }
            int nextOpsIndex = getNextOps(exp, beginPos, "\\+|-");
            char opr = exp.charAt(nextOpsIndex);
            int val = evalTerm(terms[i]);
            beginPos = nextOpsIndex + 1;
            sum = getValue(sum, opr, val);
        }
        return sum;
    }

    private static int getValue(int first, char opr, int second) {
        switch (opr) {
        case '+':
            return first + second;
        case '-':
            return first - second;
        case '*':
            return first * second;
        case '/':
            return first / second;
        }
        return 0;
    }

    private static int getNextOps(String exp, int beginPos, String ops) {
        Pattern p = Pattern.compile(ops);
        Matcher m = p.matcher(exp);
        if (m.find(beginPos)) {
            return m.start();
        }
        return -1;
    }

    private static int evalTerm(String term) {
        String[] terms = term.split("/|\\*");
        if (terms.length == 1) {
            return Integer.parseInt(term.trim());
        }
        int sum = 0;
        int beginPos = 0;
        for (int i = 0; i < terms.length; i++) {
            if (i == 0) {
                sum = Integer.parseInt(terms[i].trim());
                continue;
            }
            int nextOpsIndex = getNextOps(term, beginPos, "\\*|/");
            char opr = term.charAt(nextOpsIndex);
            int val = Integer.parseInt(terms[i].trim());
            beginPos = nextOpsIndex + 1;
            sum = getValue(sum, opr, val);
        }
        return sum;
    }
}
