package shop.prodacts;

/**
 * Created by panasyuk on 31.10.2015.
 */
public class Chicken extends Bird {
private String size;

    public Chicken(){
        setId_bird(2);
        setNameBird("Chicken");

    }
    public Chicken (String size){
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
