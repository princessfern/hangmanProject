import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Words {


    public static String getWord() throws IOException {
        LinkedList<String> words= new LinkedList<String>();
        LinkedList<String> used_words = new LinkedList<>();
        try(BufferedReader word_check = new BufferedReader(new FileReader("used_words.txt"))){
            String word = word_check.readLine();
            while(word!=null){
                used_words.add(word);
                word = word_check.readLine();
            }
        }
        try(BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
            String word = br.readLine();
            while(word!=null){
                words.add(word);
                word = br.readLine();
            }

        }catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
        Random index = new Random();
        String game_word;
        boolean no_repeat = false;
        do{
            game_word = words.get(index.nextInt(words.size()));

            if (!used_words.contains(game_word)){
                no_repeat=true;
            }
        }while(no_repeat = false);

        return game_word;
    }

}
