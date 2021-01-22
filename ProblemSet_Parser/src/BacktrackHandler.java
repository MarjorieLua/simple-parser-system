public class BacktrackHandler {
    static void BacktrackingProcess(){
        for (int track = 0; track < ParseHandler.ctrExpand; track++) {
            ParseHandler.trackParser.pop();
        }

        ParseHandler.ctrExpand = 0;

        if (ParseHandler.productions.empty()) {
            if (!(ParseHandler.historyP == null)){
                if (!ParseHandler.visible || !ParseHandler.symbolState) {
                    if (!ParseHandler.symbolState) {
                        //  epsilon: non-terminal can be removed
                        if (ParseHandler.grammarRules.get(ParseHandler.historyP.Name).LHS.Type.equals("ε")) {
                            ParseHandler.historyP = null;
                        }
                        // indicates reached the start state/node
                        else if (ParseHandler.grammarRules.get(ParseHandler.historyP.Name).LHS.Type.equals("state")) {
                            ParseHandler.accept = false;
                        }
                    }
                    else {
                        ParseHandler.popStack = true;
                        ParseHandler.symbolState = false;
                        ParseHandler.visible = true;
                        ParseHandler.trackParser.pop();
                    }
                }
                else {
                    ParseHandler.accept = false;
                    ParseHandler.trackParser.pop();
                }
            }
            else{
                ParseHandler.accept = false;
            }
        }

        else{
            // traverse
            for (int prod = ParseHandler.productions.peek().size() - 1; prod >= 0; prod--) {
                ParseHandler.trackParser.push(ParseHandler.productions.peek().get(prod));
                ParseHandler.ctrExpand++;
            }
            ParseHandler.productions.pop();
        }
    }
}