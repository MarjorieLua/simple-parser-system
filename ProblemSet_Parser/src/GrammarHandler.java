import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GrammarHandler {
    static void grammarFile(File grammar) throws Exception{
        //reading of grammar file
        FileReader fr = new FileReader(grammar);
        BufferedReader reader = new BufferedReader(fr);
        String textInput;

        for(int x = 0; ((textInput = reader.readLine()) != null); x++){
            if(!textInput.equals("")){
                // reads per line and separates the LHS and RHS
                String[] filter = textInput.replace(";", "").split(":");
                String leftHS = filter[0];
                leftHS = leftHS.trim();

                // productions are separated by the | symbol
                String[] rightHS = filter[1].split("\\|");

                if(Character.isUpperCase(leftHS.charAt(0)))
                    rightHS[0] = rightHS[0].replace("'", "");

                // grammar productions
                ParseHandler.grammarRules.put(leftHS, new Rule(leftHS, rightHS));
            }
        }
        reader.close();
    }
}
