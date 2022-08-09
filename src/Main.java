import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
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
               List<String> visuals = Arrays.asList(sb.toString().split(","));
               LinkedList<String> visualsList=new LinkedList<String>(visuals);
               System.out.println(visualsList.get(0));
//               Get word
               String word = Words.getWord().toUpperCase();
               for(char x : word.toCharArray()){
                   if(String.valueOf(x).equals(" ")||String.valueOf(x).equals(".")||String.valueOf(x).equals(":")||String.valueOf(x).equals("-")||String.valueOf(x).equals("'")||String.valueOf(x).matches("%d")){
                       System.out.print(x);
                   }else{
                       System.out.print("_");
                   }
               }
               System.out.println("\n"+word);

//               Start guessing
               System.out.print("Start Guessing!\n");
               Boolean game = true;
               FileWriter progress = new FileWriter("progress.txt");
               do{
                   String guess = scanner.next("[a-zA-Z]");
                   StringBuilder updates = new StringBuilder();
                   if(word.contains(guess.toUpperCase())){
//                       System.out.println("present!");
                       System.out.println(visualsList.get(0));
                       for(char x : word.toCharArray()) {
                           if (String.valueOf(x).equals(" ") || String.valueOf(x).equals(".") || String.valueOf(x).equals(":") || String.valueOf(x).equals("-") ||
                                   String.valueOf(x).equals("'") || String.valueOf(x).matches("\\d") || String.valueOf(x).equals(guess.toUpperCase())) {
                               updates.append(x);
                           } else {
                               updates.append("_");
                           }
                       }

                       progress.write(updates.toString());
                       progress.close();
                       Scanner savedProgress = new Scanner(Paths.get("progress.txt"));
                       while(savedProgress.hasNext()){
                           System.out.format("%s ", savedProgress.next());
                       }

                   }else{
                       System.out.println("Not Present!");
                       visualsList.pop();
                       System.out.println(visualsList.get(0));
                       Scanner savedProgress = new Scanner(Paths.get("progress.txt"));
                       while(savedProgress.hasNext()){
                           System.out.format("%s ", savedProgress.next());
                       }
                   }
                }while(game==true);


           } catch (IOException e) {
               e.printStackTrace();
           }

        }
    }
}
