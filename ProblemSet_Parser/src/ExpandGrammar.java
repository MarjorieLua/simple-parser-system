public class ExpandGrammar {
    // expands a production rule
    static void expand(){
        String peak = ParseHandler.trackParser.peek().Name;
        CheckType checkPeak = ParseHandler.trackParser.peek();

        switch (checkPeak.Type) {
            case "kleeneStar" -> {
                ParseHandler.symbolState = true;
                ParseHandler.popStack = false;
            }
        }

        if(ParseHandler.popStack) ParseHandler.trackParser.pop();

        ParseHandler.ctrExpand = 0;

        //loop for expanding the peak top of the parser
        for(int top = ParseHandler.grammarRules.get(peak).RHS.get(0).size()-1; top >= 0; top--){
            ParseHandler.trackParser.push(ParseHandler.grammarRules.get(peak).RHS.get(0).get(top));
            ParseHandler.ctrExpand++;
        }

        //checking for more than 1 rule of grammar exists
        if(ParseHandler.grammarRules.get(peak).RHS.size() > 1){
            ParseHandler.historyP = checkPeak;
            for(int g = ParseHandler.grammarRules.get(peak).RHS.size()-1; g >= 1 ; g--){
                ParseHandler.productions.push(ParseHandler.grammarRules.get(peak).RHS.get(g));
            }
        }
    }
}
