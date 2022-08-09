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
}
