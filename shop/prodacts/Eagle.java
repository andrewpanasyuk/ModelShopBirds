package shop.prodacts;

/**
 * Created by panasyuk on 31.10.2015.
 */
public class Eagle extends Bird {

    private int age;
    public Eagle(){
        setId_bird(1);
        setNameBird("Eagle");
    }

    public Eagle(int age) {
        setId_bird(1);
        setNameBird("Eagle");
        this.age = age;
    }
}
