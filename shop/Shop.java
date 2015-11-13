package shop;

import connectDB.CreateDerbyTabl;
import connectDB.DBWorker;
import shop.customer.CustomerDB;
import shop.customer.CustomerL;
import shop.customer.CustomerShop;
import shop.report.ReportC;
import shop.report.ReportDB;
import shop.report.ReportsShop;
import shop.stock.StockDB;
import shop.stock.StockCol;
import shop.stock.StockShop;
import shop.transaction.TransactionCol;
import shop.transaction.TransactionDb;
import shop.transaction.TransactionShop;


/**
 * Created by panasyuk on 01.11.2015.
 */
public class Shop {
    private DBWorker bdw;
    private StockShop stock;
    private CustomerShop customers;
    private TransactionShop transactionShop;
    private ReportsShop reportsShop;

    public Shop() {
        bdw = new DBWorker("jdbc:mysql://localhost:3306/birdsshop");
        stock = new StockDB(bdw);
        customers = new CustomerDB(bdw);
        transactionShop = new TransactionDb(bdw);
        reportsShop = new ReportDB(bdw, transactionShop, stock);

    }
    public Shop(String i) {

        bdw = new DBWorker("jdbc:derby:birdsshop;create=true");
        CreateDerbyTabl derbyTabl = new CreateDerbyTabl(bdw);
            if (i  == "clear") {
                derbyTabl.clear();

            } else {
                derbyTabl.CreateTabl();
            }
        stock = new StockDB(bdw);
        customers = new CustomerDB(bdw);
        transactionShop = new TransactionDb(bdw);
        reportsShop = new ReportDB(bdw, transactionShop, stock);
    }

    public Shop(int i) {
        customers = new CustomerL();
        stock = new StockCol();
        transactionShop = new TransactionCol(stock);
        reportsShop = new ReportC(transactionShop, stock);
    }

    public CustomerShop getCustomers() {
        return customers;
    }

    public StockShop getStock() {
        return stock;
    }

    public void setStock(StockShop stock) {
        this.stock = stock;
    }


    public ReportsShop getReportsShop() {
        return reportsShop;
    }

    public void setReportsShop(ReportsShop reportsShop) {
        this.reportsShop = reportsShop;
    }

    public TransactionShop getTransactionShop() {
        return transactionShop;
    }

}
