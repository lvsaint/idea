import java.util.Arrays;
import java.util.List;

public class FormulaPart {
    private List<String> formula;
    private List<String> assignment;
    private String calc;

    public static FormulaPart build(String f, String ass, String calc) {
        FormulaPart fp = new FormulaPart();
        String[] fparts = f.split(",");
        fp.setFormula(Arrays.asList(fparts));

        fp.setAssignment(Arrays.asList(ass.split(",")));
        
        fp.setCalc(calc);
        
        return fp;
    }

    /**
     * @return the formula
     */
    public List<String> getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(List<String> formula) {
        this.formula = formula;
    }

    /**
     * @return the assignment
     */
    public List<String> getAssignment() {
        return assignment;
    }

    /**
     * @param assignment the assignment to set
     */
    public void setAssignment(List<String> assignment) {
        this.assignment = assignment;
    }

    /**
     * @return the calc
     */
    public String getCalc() {
        return calc;
    }

    /**
     * @param calc the calc to set
     */
    public void setCalc(String calc) {
        this.calc = calc;
    }

}