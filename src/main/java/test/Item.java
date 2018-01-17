package test;

public class Item {
    private String name = "";  //name of the item
    private int count = 0; //couunt of the item
    private int tmpCount = 0;  //used to transfer data for purchase activities

    public Item(String name, int num){
        this.name = name;
        this.count = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(Item i){
        count += i.getTmpCount();
    }

    public void minus(Item i){
        count -= i.getTmpCount();
    }

    public int getTmpCount() {
        return tmpCount;
    }

    public void setTmpCount(int count) {
        this.tmpCount = count;
    }

}
