package shop.stock;

import shop.prodacts.Bird;

/**
 * Created by panasyuk on 01.11.2015.
 */
public interface StockWork {
    public void addNewTypeProducts(int id, String bird, int qnt, double price);
    //public void addBirdsOnStock();
    public void takeCurrentStock();
    public void updatePosition (String wot, String name, String parametr);

}
