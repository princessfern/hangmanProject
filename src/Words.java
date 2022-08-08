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
        return words.get(index.nextInt(words.size()));
    }

}
