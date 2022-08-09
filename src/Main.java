import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void outputWord(String word, UpdatedString updates, ArrayList<String> guesses){
        for(char x : word.toCharArray()) {
            if (String.valueOf(x).equals(" ") || String.valueOf(x).equals(".") || String.valueOf(x).equals(":") || String.valueOf(x).equals("-") ||
                    String.valueOf(x).equals("'") || String.valueOf(x).matches("\\d") || guesses.contains(String.valueOf(x))) {
                updates.addTo(String.valueOf(x));
            } else {
                updates.addTo("_");
            }
        }
        System.out.println(updates.get());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Begin Game? Y/N");
        String input = scanner.next();
        if(input.equalsIgnoreCase("y")){
            StringBuilder sb = new StringBuilder();
           try(BufferedReader br = new BufferedReader(new FileReader("visuals.txt"))){
               String line = br.readLine();
               while(line!=null){
                   sb.append(line);
                   sb.append(System.lineSeparator());
                   line = br.readLine();
               }
               LinkedList<String> visualsList= new LinkedList<>(Arrays.asList(sb.toString().split(",")));
               System.out.println(visualsList.get(0));
//               Get word
               String word = Words.getWord().toUpperCase();
               for(char x : word.toCharArray()){
                   if(String.valueOf(x).equals(" ")||String.valueOf(x).equals(".")||String.valueOf(x).equals(":")||String.valueOf(x).equals("-")||String.valueOf(x).equals("'")||
                           String.valueOf(x).matches("\\d")){
                       System.out.print(x);
                   }else{
                       System.out.print("_");
                   }
               }
               System.out.println("\n"+word);

//               Start guessing
               System.out.print("Start Guessing!\n");
               Boolean game = true;

               ArrayList<String> guesses = new ArrayList<String>();
               do{
//                   StringBuilder updates = new StringBuilder();
                   UpdatedString updates = new UpdatedString();
                   String guess = scanner.next("[a-zA-Z]").toUpperCase();
                   guesses.add(guess);
                   if(word.contains(guess)){
//                       System.out.println("present!");
                       System.out.println(visualsList.get(0));
                       outputWord(word, updates, guesses);

                   }else{
                       System.out.println("Not Present!");
                       visualsList.pop();
                       System.out.println(visualsList.get(0));
                       outputWord(word, updates, guesses);
                       if(visualsList.size()==1){
                           game = false;
                       }
                   }
                }while(game==true);


           } catch (IOException e) {
               e.printStackTrace();
           }

        }
    }
}
