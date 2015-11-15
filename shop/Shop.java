package shop;

import connectDB.*;
import shop.customer.*;
import shop.report.*;
import shop.stock.*;
import shop.transaction.*;


/**
 * Created by panasyuk on 01.11.2015.
 */
public class Shop {
    private StockShop stock;
    private CustomerShop customers;
    private TransactionShop transactionShop;
    private ReportsShop reportsShop;

    public Shop() {
        DataSource dataSource = new DataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/birdsshop");
        stock = new StockDB(dataSource);
        customers = new CustomerDB(dataSource);
        transactionShop = new TransactionDb(dataSource);
        reportsShop = new ReportDB(dataSource, transactionShop, stock);

    }
    public Shop(String i) {
        DataSource dataSource = new DataSource("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:berdsshop;create=true");
        CreateDerbyTabl derbyTabl = new CreateDerbyTabl(dataSource);
            if (i  == "clear") {
                derbyTabl.clear();
            } else {
                derbyTabl.CreateTabl();
            }
        stock = new StockDB(dataSource);
        customers = new CustomerDB(dataSource);
        transactionShop = new TransactionDb(dataSource);
        reportsShop = new ReportDB(dataSource, transactionShop, stock);
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
