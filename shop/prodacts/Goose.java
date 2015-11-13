package shop.prodacts;

/**
 * Created by panasyuk on 31.10.2015.
 */
public class Goose extends Bird {
    private double weight;
    public Goose(){
        setId_bird(3);
        setNameBird("Goose");
    }
    public Goose(double weight){
        this.weight = weight;
        setNameBird("Goose");
    }
}
