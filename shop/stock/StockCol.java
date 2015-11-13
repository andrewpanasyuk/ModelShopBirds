package shop.stock;
import shop.Shop;
import shop.TestData;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class StockCol extends StockShop {
private TestData td;
private Shop shop;
    public StockCol(){
        td = new TestData();
        setStockList(td.creatTestStock());
    }

    @Override
    public void addNewTypeProducts(int id, String bird, int qnt, double price) {

        getStockList().add(new StockShop(id, bird, qnt, price));
    }

    @Override
    public void takeCurrentStock() {
        super.takeCurrentStock();
    }

    @Override
    public void updatePosition(String wot, String name, String parametr) {
        for (int i = 0; i < getStockList().size(); i ++){
            if (getStockList().get(i).getBird().equals(name)){
                if (wot.equals("qnt")){
                    getStockList().get(i).setOnStock(Integer.valueOf(parametr));
                } else {
                    getStockList().get(i).setPrice(Double.valueOf(parametr));
                }
            }
        }

        System.out.println(wot + " " + name + " " + parametr);
        super.updatePosition(wot, name, parametr);
    }
}
