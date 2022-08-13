import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class UsedWords {
    private ArrayList<String> used_words= new ArrayList<>();


    public ArrayList<String> getUsedWords(){

        try(BufferedReader br= new BufferedReader(new FileReader("used_words.txt"))){
            String word = br.readLine();
            while(word!=null){
                this.used_words.add(word);
                word = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return used_words;
    }

    public void writeFile() throws IOException {
        FileWriter fw = new FileWriter("used_words.txt");
        for(String word : this.used_words){
            fw.append(word+"\n");
        }
        fw.close();
    }

    public void addWord(String word) throws IOException {
        this.used_words.add(word);
        writeFile();
    }


}
