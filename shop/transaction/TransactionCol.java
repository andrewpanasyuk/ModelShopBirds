package shop.transaction;


import shop.stock.StockShop;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by panasyuk on 04.11.2015.
 */
public class TransactionCol extends TransactionShop {
    StockShop stockShop;

    public TransactionCol(){

    }
    public TransactionCol(StockShop stockShop){
this.stockShop=stockShop;
    }

    @Override
    public int createTransaction(String name_customer, String name_bird, int qnt) {
        for (int i = 0; i < stockShop.getStockList().size(); i++){
            if (stockShop.getStockList().get(i).getBird().equals(name_bird)){
                if (stockShop.getStockList().get(i).getOnStock() < qnt){
                    return 0;
                } else {
                    stockShop.getStockList().get(i).setOnStock(stockShop.getStockList().get(i).getOnStock() - qnt);
                }
            }
        }


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        String dataTxt = sdf.format(date);
        getTransactionShopList().add(new TransactionShop(getIdTransaction()+1, dataTxt, name_customer, name_bird, qnt));
        return 1;
    }

    @Override
    public List<TransactionShop> getTransactionShopList() {
        return super.getTransactionShopList();
    }

    @Override
    public void setTransactionShopList(List<TransactionShop> transactionShopList) {
        super.setTransactionShopList(transactionShopList);
    }

    @Override
    public void takeAllTransaction() {
        super.takeAllTransaction();
    }
}
