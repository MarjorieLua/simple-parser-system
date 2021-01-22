import java.util.ArrayList;

public class Rule {

    CheckType LHS; //LHS rules
    ArrayList<ArrayList<CheckType>> RHS = new ArrayList<>(); // RHS rules

    boolean kleene = false;
    boolean ε = false;
    boolean many = false;

    public Rule() {
    }

    public Rule(String left, String[] right) {
        for (int i = 0, rightLength = right.length; i < rightLength; i++) {
            String rule = right[i];

            if (right.length > 1) {
                many = true;
            }
            // for epsilon condition
            if (!rule.equals(" ε ")) {
                rule = rule.trim();
                String[] rules = rule.split(" ");

                ArrayList<CheckType> chkType = new ArrayList<>();

                for (int j = 0, rulesLength = rules.length; j < rulesLength; j++) {
                    String r = rules[j];
                    if (r.length() != 1) {
                        if (r.charAt(r.length() - 1) == '+')
                            chkType.add(new CheckType(r.replace("+", ""), "kleeneStar"));
                        else
                            chkType.add(new CheckType(r));
                    }
                    else {
                        chkType.add(new CheckType(r));
                    }
                }
                RHS.add(chkType);
            }
            else {
                ε = true;
            }
        }

        if (!kleene) {
            if (ε) {
                LHS = new CheckType(left, "ε");
            }
            else {
                LHS = new CheckType(left);
            }
        }
        else {
            LHS = new CheckType(left, "kleeneStar");
        }
    }
}
