import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Hangman {
    private Words words = new Words();

    private void outputWord(String word, UpdatedString updates, ArrayList<String> guesses, ArrayList<String> word_guesses){

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
        try {
            AudioPlayer audioPlayer = new AudioPlayer();

            audioPlayer.play();
            Scanner sc = new Scanner(System.in);

            boolean good = false;
            do{
                System.out.println("Begin Game? Y/N");
                String input = scanner.next();

                if (input.equalsIgnoreCase("y")) {
                    good = true;
                    System.out.println("-----------------------------------------Instructions-----------------------------------------\n----------------------Guess using a letter or word!----------------------\n-----If you guess a word, you can still guess the letters within that word on other turns-----");
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new FileReader("visuals.txt"));
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    LinkedList<String> visualsList = new LinkedList<>(Arrays.asList(sb.toString().split(",")));
//               Get word
                    System.out.println("Game mode: Easy(1)\t\tNormal(2)\t\tHard(3)\n");
                    Scanner scanMode = new Scanner(System.in);
                    int mode;
                    boolean continue_game = false;
                    do{
                        mode = scanMode.nextInt();
                        if(mode>=1 && mode<=3){
                            continue_game = true;
                        }else{
                            System.out.println("Invalid Input. Please re-enter.\n");
                        }
                    }while(continue_game==false);

                    String word = this.words.getWord(mode).toUpperCase();


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
                            if(g.equals(guess)){
                                present = true;
                            }
                        }
                        for(String wg : word_guesses){
                            if(wg.equals(guess)){
                                present = true;
                            }
                        }

                        if (present==true) {
                            System.out.println("Already guessed. Try again!\n");

                        } else if (present==false && word.contains(guess)) {
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

                            if(guess.length()>1){
                                word_guesses.add(guess);
                            }else{
                                guesses.add(guess);
                            }

                            System.out.println(visualsList.get(0));
                            outputWord(word, updates, guesses, word_guesses);
                            if (visualsList.size() == 2) {
//                                game = false;
                                System.out.println("Answer: " + word);
                                playHangman();
                            }
                        }
                    } while (game == true);
                }else if(input.equalsIgnoreCase("n")){
                    FileWriter fw = new FileWriter("used_words.txt");
                    fw.close();
                    audioPlayer.stop();
                    System.exit(1);
                }else{
                    System.out.println("Incorrect input");
                }
            } while(good==false);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
