package shop.transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class TransactionShop implements Transaction {
    private static int idTransaction = 0;
    private int currentID;
    private String data;
    private String name_customer;
    private String name_bird;
    private int qnt;

    private List<TransactionShop> transactionShopList = new ArrayList<>();

    public TransactionShop() {
        currentID  = idTransaction;
    }

    public TransactionShop(int id, String data, String name_customer, String name_bird, int qnt) {
        idTransaction++;
        this.currentID = id;
        this.data = data;
        this.name_customer = name_customer;
        this.name_bird = name_bird;
        this.qnt = qnt;

    }

    @Override
    public int createTransaction(String name_customer, String name_bird, int qnt) {

        return 0;
    }

    @Override
    public void takeAllTransaction() {

    }

    public static int getIdTransaction() {
        return idTransaction;
    }

    public static void setIdTransaction(int idTransaction) {
        TransactionShop.idTransaction = idTransaction;
    }

    public String getName_bird() {
        return name_bird;
    }

    public void setName_bird(String name_bird) {
        this.name_bird = name_bird;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }




    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public List<TransactionShop> getTransactionShopList() {

        return transactionShopList;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    public void setTransactionShopList(List<TransactionShop> transactionShopList) {
        this.transactionShopList = transactionShopList;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
