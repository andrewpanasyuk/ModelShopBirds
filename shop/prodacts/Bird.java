package shop.prodacts;

import org.reflections.Reflections;

import java.util.*;

/**
 * Created by panasyuk on 31.10.2015.
 */
public abstract class Bird {
    private int id_bird = 0;

    private String nameBird;
    private double price;
    private static Set<Class<? extends Bird>> subTypes;

    public  Bird(){

            }

    public int getId_bird() {
        return id_bird;
    }

    public void setId_bird(int id_bird) {
        this.id_bird = id_bird;
    }

    public String getNameBird() {
        return nameBird;
    }

    public void setNameBird(String nameBird) {
        this.nameBird = nameBird;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static Set<Class<? extends Bird>> getSubTypes() {
        Reflections reflections = new Reflections();
        subTypes = reflections.getSubTypesOf(Bird.class);
        return subTypes;
    }
}
