public class UpdatedString {
    public StringBuilder updates;
    public UpdatedString(){
        updates=new StringBuilder();
    }

    public void addTo(String x){
        updates.append(x);
    }

    public String get(){
        return updates.toString();
    }

    public void replace(int start, int end, String word){
        updates.replace(start, end, word);
    }
}
