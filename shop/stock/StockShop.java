package shop.stock;

import shop.prodacts.Bird;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class StockShop implements StockWork {
    private List<StockShop> stockList = new ArrayList<>();
    private int id;
    private String bird;
    private int onStock;
    private double price;

    public StockShop(int id, String bird, int onStock, double price) {
        this.id = id;
        this.bird = bird;
        this.onStock = onStock;
        this.price = price;
    }

    public StockShop() {
        id++;
    }


    @Override
    public void addNewTypeProducts(int id, String bird, int qnt, double price) {

    }

    @Override
    public void takeCurrentStock() {
    }

    @Override
    public void updatePosition(String wot, String name, String parametr) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBird() {
        return bird;
    }

    public void setBird(String bird) {
        this.bird = bird;
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<StockShop> getStockList() {
        return stockList;
    }

    public List<Bird> compare(List<StockShop> sh) {
        List<String> stock = new ArrayList<>();
        List<Bird> rezult = new ArrayList<>();

        for (int i = 0; i < sh.size(); i++) {
            stock.add(sh.get(i).getBird());
        }

        List<Class> l1 = new ArrayList<>();
        for (Class c : Bird.getSubTypes()) {
            l1.add(c);
        }
        for (int i = 0; i < l1.size(); i++) {
            if (!stock.contains(l1.get(i).getSimpleName())) {
                try {
                    rezult.add((Bird) l1.get(i).newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return rezult;
    }

    public void setStockList(List<StockShop> stockList) {
        this.stockList = stockList;
    }
}
