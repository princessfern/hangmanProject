import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Hangman {
    public void outputWord(String word, UpdatedString updates, ArrayList<String> guesses, ArrayList<String> word_guesses){

        for(char x : word.toCharArray()) {
            Boolean present = false;
            for(String g : guesses){
                if(g.equals(String.valueOf(x))){
                    present = true;
                }
            }
            if (String.valueOf(x).equals(" ") || String.valueOf(x).equals(".") || String.valueOf(x).equals(":") || String.valueOf(x).equals("-") ||
                    String.valueOf(x).equals("'") || String.valueOf(x).equals(",") || String.valueOf(x).matches("\\d") ||
                    guesses.contains(String.valueOf(x))|| present) {
                updates.addTo(String.valueOf(x));
            } else {
                updates.addTo("_");
            }
        }

        for(String g: word_guesses){
            for(String w : word.split(" ")){
                if(g.equals(w)){
                    updates.replace(word.indexOf(g),word.indexOf(g)+w.length(), w);
                    if(word.lastIndexOf(g)!=word.indexOf(g)){
                        updates.replace(word.lastIndexOf(g),word.lastIndexOf(g)+w.length(), w);
                    }
                }
            }
        }

        if(word.equals(updates.get())){
            System.out.println("YOU WIN!!!!!!");
            System.out.println(updates.get());
            playHangman();
        }else{
            System.out.println(updates.get());
        }
    }

    public void playHangman(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Begin Game? Y/N");
        try {
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                System.out.println("-----------------------------------------Instructions-----------------------------------------\n----------------------Guess using a letter or word, or guess the answer!----------------------\n-----If you guess a word, you can still guess the letters within that word on other turns-----");
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader("visuals.txt"));
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                LinkedList<String> visualsList = new LinkedList<>(Arrays.asList(sb.toString().split(",")));
                System.out.println(visualsList.get(0));
//               Get word
                String word = Words.getWord().toUpperCase();
                for (char x : word.toCharArray()) {
                    if (String.valueOf(x).equals(" ") || String.valueOf(x).equals(".") || String.valueOf(x).equals(":") || String.valueOf(x).equals("-") || String.valueOf(x).equals("'") ||
                            String.valueOf(x).matches("\\d")) {
                        System.out.print(x);
                    } else {
                        System.out.print("_");
                    }
                }
                System.out.println("\n"+word);

//               Start guessing
                System.out.print("\nStart Guessing!\n");
                Boolean game = true;

                ArrayList<String> guesses = new ArrayList<>();
                ArrayList<String> word_guesses = new ArrayList<>();
                do {
                    UpdatedString updates = new UpdatedString();
                    String guess = scanner.nextLine().toUpperCase();

//                    check if previously guessed
                    Boolean present = false;
                    for(String g : guesses){
                        if(g.contains(guess)){
                            present = true;
                        }
                    }

                    if (present==true) {
                        System.out.println("Already used. Try again!\n");

                    } else if (present==false && word.contains(guess)) {
//                       System.out.println("present!");
                        if(guess.length()>1){
                            System.out.println(visualsList.get(0));
                            word_guesses.add(guess);
                            outputWord(word, updates, guesses, word_guesses);
                        }
                        else{
                            System.out.println(visualsList.get(0));
                            guesses.add(guess);
                            outputWord(word, updates, guesses, word_guesses);
                        }

                    } else {
                        System.out.println("Not Present!");
                        visualsList.pop();
                        System.out.println(visualsList.get(0));
                        outputWord(word, updates, guesses, word_guesses);
                        if (visualsList.size() == 2) {
                            game = false;
                            System.out.println("Answer: " + word);
                        }
                    }
                } while (game == true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
