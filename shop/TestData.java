package shop;

import shop.customer.CustomerShop;
import shop.prodacts.Chicken;
import shop.prodacts.Eagle;
import shop.prodacts.Goose;
import shop.stock.StockShop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class TestData {
    public TestData() {

    }

    public List<StockShop> creatTestStock() {
        List<StockShop> stockShops = new ArrayList<>();
        Goose goose = new Goose();
        stockShops.add(new StockShop(goose.getId_bird(), goose.getNameBird(), 38, 8.0));
        Eagle eagle = new Eagle();
        stockShops.add(new StockShop(eagle.getId_bird(), eagle.getNameBird(), 15, 32.4));
        Chicken chicken = new Chicken();
        stockShops.add(new StockShop(chicken.getId_bird(), chicken.getNameBird(), 121, 1.5));
        return stockShops;

    }

    public List<CustomerShop> creatTestCustomer() {
        List<CustomerShop> list = new ArrayList<>();
        list.add(new CustomerShop(1, "Ivanov"));
        list.add(new CustomerShop(2, "Petrov"));
        list.add(new CustomerShop(3, "Jonson"));
        list.add(new CustomerShop(4, "Pupkin"));

        return list;
    }


}

