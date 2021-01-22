import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        try{
            File file = new File("grammar.txt");
            File file_input = new File("input.txt");
            FileReader reader = new FileReader(file_input);
            BufferedReader filereader = new BufferedReader(reader);
            BufferedWriter filewriter = new BufferedWriter(new FileWriter("output.txt"));

            GrammarHandler.grammarFile(file);
            String textInput;
            ParseHandler.trackParser.push(new CheckType("start"));

            for(int x = 0;((textInput = filereader.readLine()) != null); x++ ){
                if(!textInput.equals("")){
                    ParseHandler.output = ParseHandler.output + textInput;
                    textInput = textInput.replaceAll("\\s","");
                    char[] c = textInput.toCharArray();

                    for(int chars = c.length-1; chars >= 0; chars--){
                        ParseHandler.trackInputs.push(c[chars]);
                    }

                    ParseHandler.parseProcess();
                    ParseHandler.clearContent();
                }

                ParseHandler.output = ParseHandler.output + '\n';
            }

            reader.close();
            filewriter.write(ParseHandler.output);
            filewriter.close();

        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
}