package shop.report;

import connectDB.DBWorker;
import shop.stock.StockShop;
import shop.transaction.TransactionShop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by panasyuk on 04.11.2015.
 */
public class ReportDB extends ReportsShop {
    private DBWorker dbWorker;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private TransactionShop transactionShop;
    private StockShop stockShop;
    private Connection connection;


    public ReportDB(DBWorker dbWorker, TransactionShop transactionShop, StockShop stockShop) {
        this.dbWorker = dbWorker;
        this.connection = dbWorker.getConnection();
        this.transactionShop = transactionShop;
        this.stockShop = stockShop;
    }

    @Override
    public void selectCustomerAccordingProduct(String name_prodact) {
        connection = dbWorker.getConnection();
        HashSet<String> nameCustomers = new HashSet<>();
        whoBuysBirds(nameCustomers, name_prodact, connection);
        Set<String> a = new HashSet<>();


        setListReportNameCustomer(new Object[4][nameCustomers.size() + 1]);
        listReportNameCustomer[0][0] = "#";
        listReportNameCustomer[1][0] = "Customer";
        listReportNameCustomer[2][0] = "Total qnt.";
        listReportNameCustomer[3][0] = "Total money";
        int n = 1;
        double price = 0;

        String select = "SELECT * FROM customer JOIN transactions on (customer.ID = transactions.fk_customer_id) JOIN secondary_transaction ON (transactions.id_transaction = secondary_transaction.fk_transaction_id) JOIN stock_shop ON (secondary_transaction.fk_podact_id = stock_shop.id_prodact) WHERE name = ? AND name_prodact = ?";
        try {


            Iterator<String> iterator = nameCustomers.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                int qnt = 0;
                preparedStatement = connection.prepareStatement(select);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, name_prodact);
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
                listReportNameCustomer[0][n] = n;
                listReportNameCustomer[1][n] = name;
                while (resultSet.next()) {
                    qnt = qnt + resultSet.getInt("qnt_birds");
                    price = resultSet.getDouble("price_prodact");
                }
                listReportNameCustomer[2][n] = qnt;
                listReportNameCustomer[3][n] = qnt * price;
                n++;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //dbWorker.putConnect(connection);
    }

    @Override
    public void totalCash() {
        selectProductsAccordingCustomer("-- ALL CUSTOMERS: --");
    }

    public void whoBuysBirds(HashSet nameCustomers, String name_prodact, Connection connection) {
        String select = "SELECT name FROM customer RIGHT JOIN transactions on (customer.ID = transactions.fk_customer_id) JOIN secondary_transaction ON (transactions.id_transaction = secondary_transaction.fk_transaction_id) JOIN stock_shop ON (secondary_transaction.fk_podact_id = stock_shop.id_prodact) WHERE name_prodact = ?";
        try {
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name_prodact);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                nameCustomers.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectProductsAccordingCustomer(String name_customer) {
        connection = dbWorker.getConnection();
        setListReportNameCustomer(new Object[4][2]);
        listReportNameCustomer[0][0] = "#";
        listReportNameCustomer[1][0] = "Name Customer";
        listReportNameCustomer[2][0] = "qnt transactions";
        listReportNameCustomer[3][0] = "Total money";
        int qnt_t = 0;
        double money = 0;

        if (name_customer != "-- ALL CUSTOMERS: --") {
            String select = "SELECT * FROM customer RIGHT JOIN transactions on (customer.id = transactions.fk_customer_id) JOIN secondary_transaction ON (transactions.id_transaction = secondary_transaction.fk_transaction_id) JOIN stock_shop ON (secondary_transaction.fk_podact_id = stock_shop.id_prodact) WHERE name = ?";
            try {
                preparedStatement = connection.prepareStatement(select);
                preparedStatement.setString(1, name_customer);
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    qnt_t = qnt_t + 1;
                    money = money + resultSet.getDouble("price_prodact") * resultSet.getInt("qnt_birds");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String select = "SELECT * FROM customer RIGHT JOIN transactions on (customer.id = transactions.fk_customer_id) JOIN secondary_transaction ON (transactions.id_transaction = secondary_transaction.fk_transaction_id) JOIN stock_shop ON (secondary_transaction.fk_podact_id = stock_shop.id_prodact)";
            try {
                preparedStatement = connection.prepareStatement(select);
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    qnt_t = qnt_t + 1;
                    money = money + resultSet.getDouble("price_prodact") * resultSet.getInt("qnt_birds");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        listReportNameCustomer[0][1] = 1;
        listReportNameCustomer[1][1] = name_customer;
        listReportNameCustomer[2][1] = qnt_t;
        listReportNameCustomer[3][1] = money;


        //dbWorker.putConnect(connection);
    }

}
