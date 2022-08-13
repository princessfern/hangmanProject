import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Words {


    public static String getWord() throws IOException {
        LinkedList<String> words= new LinkedList<String>();
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
        UsedWords usedWords = new UsedWords();
        do{
            game_word = words.get(index.nextInt(words.size()));

            if (!usedWords.getUsedWords().contains(game_word)){
                no_repeat=true;
            }
        }while(no_repeat = false);

        usedWords.addWord(game_word);
        return game_word;

    }

}
