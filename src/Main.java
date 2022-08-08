import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Begin Game? Y/N");
        String input = scanner.next();
        if(input.equalsIgnoreCase("y")){
           try(BufferedReader br = new BufferedReader(new FileReader("visuals.txt"))){
               String line = br.readLine();
               while(line!=null){
                   System.out.println(line);
                   line = br.readLine();
               }
//               Get word
               String word = Words.getWord();
               for(char x : word.toCharArray()){
                   if(String.valueOf(x).equals(" ")||String.valueOf(x).equals(".")||String.valueOf(x).equals(":")){
                       System.out.print(x);
                   }else{
                       System.out.print("_");
                   }
               }
               System.out.println("\n"+word);
           } catch (IOException e) {
               e.printStackTrace();
           }

        }
    }
}
