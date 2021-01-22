public class CheckType {
    String Name;
    String Type;

    public CheckType(){
    }

    public CheckType(String name, String type){
        this.Name = name;
        this.Type = type;
    }

    public CheckType(String name){
        Name = name;
        Type = "state";
    }
}