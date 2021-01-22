import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ParseHandler {

    static HashMap<String, Rule> grammarRules = new HashMap<String, Rule>(); // hashmap for the grammar rules
    static Stack<Character> trackInputs = new Stack<Character>(); // stack for inputs
    static Stack<CheckType> trackParser = new Stack<CheckType>(); // stack for parser to backtrack
    static Stack<ArrayList<CheckType>> productions = new Stack<ArrayList<CheckType>>(); // stack for productions
    static CheckType historyP = new CheckType(); // history of productions for backtrack process
    static int ctrExpand = 0; //tracks expanding

    static boolean accept = true;
    static boolean visible = true;
    static boolean popStack = true;
    static boolean symbolState = false;

    static String output = "";

    static void clearP(){
        historyP = new CheckType();
        productions.clear();
        ctrExpand = 0;
    }

    static void clearContent(){
        trackInputs.clear();
        trackParser.clear();
        trackParser.push(new CheckType("start"));
        popStack = true;
        visible = true;
        historyP = new CheckType();
        productions.clear();
        ctrExpand = 0;
        accept = true;
    }

    static void checkParse() {
        int checkParser = trackParser.size();
        for (int cp = 0; cp < checkParser; cp++) {
            if (grammarRules.get(trackParser.peek().Name).LHS.Type.equals("ε")) {
                trackParser.pop();
            }
            else {
                if (symbolState && !ParseHandler.visible) {
                    symbolState = false;
                    ParseHandler.visible = true;
                    trackParser.pop();
                }
            }
        }
    }

    static void printOutput() {
        if (trackInputs.isEmpty()) {
            if (trackParser.isEmpty()) {
                // accepted result
                output = output + " - ACCEPT";
            }
            else {
                // retrieve missing token
                output = output + " - REJECT. Missing Token '" + grammarRules.get(trackParser.peek().Name).RHS.get(0).get(0).Name + "'";
            }
        }
        else {
            //retrieve offending token
            output = output + " - REJECT. Offending Token '" + trackInputs.peek() + "'";
        }
    }

     static void parseProcess(){
        String peak = "";
         // parsing process: checks each character and matches the production rules
        for(int i = 0; (!trackInputs.isEmpty()); i++){
            if(!(trackParser.isEmpty())) peak = trackParser.peek().Name;
            else {
                accept = false;
                break;
            }
            // uppercase scenario
            if(Character.isUpperCase(peak.charAt(0))){
                if(!(grammarRules.get(trackParser.peek().Name).RHS.get(0).get(0).Name.equals(trackInputs.peek().toString()))) {
                    BacktrackHandler.BacktrackingProcess();
                }
                else {
                    clearP();
                    trackInputs.pop();
                    trackParser.pop();

                    if(symbolState)
                        ParseHandler.visible = false;
                }
            }
            // lowercase scenario
            else if(!Character.isUpperCase(peak.charAt(0))) ExpandGrammar.expand();

            if(!accept) break;
        }
        checkParse();
        printOutput();
    }
}
