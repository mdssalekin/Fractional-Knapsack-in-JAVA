

public class Item {
    private  String name;
    private int value;
    private double size;
    private double ratio;


    public Item(String name, int value, double size){
        this.name = name;
        this.value = value;
        this.size = size;
        this.ratio = value/size;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }
    public double getSize(){
        return size;
    }

    public double getRatio(){
        return ratio;
    }

    @Override
    public String toString(){
        return "\nname: "+ this.name + "\nValue: " + this.value + "\nSize: " + this.size + "\n\n";
    }


}
