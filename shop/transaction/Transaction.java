package shop.transaction;

/**
 * Created by panasyuk on 02.11.2015.
 */
public interface Transaction {
//    static int a = 0;
    public int createTransaction(String name_customer, String name_bird, int qnt);
    public void takeAllTransaction();
}
