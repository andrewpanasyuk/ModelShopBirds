package shop.customer;

import connectDB.DBWorker;

import java.sql.*;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class CustomerDB extends CustomerShop {
    DBWorker dbWorker;
    PreparedStatement ps;
    ResultSet resultSet;

    public CustomerDB(DBWorker dbWorker){
        this.dbWorker = dbWorker;
        takeAllCustomer();


    }
    public int maxNumberCustomerInDerby(){
        int maxNumber = 0;
        String start = "SELECT MAX(id) AS id FROM customer";
        Statement s = null;
        try {
            s = dbWorker.getConnection().createStatement();
            s.execute(start);
            ResultSet rs = s.getResultSet();
            while (rs.next()){
                maxNumber = rs.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setId_customer(maxNumber);
        setId(maxNumber);
        return maxNumber;
    }

    @Override
    public void addNewCustomer(String name) {
        CustomerShop customer = new CustomerShop(name);
        String addCustomer = "INSERT INTO customer (id, name) values (?, ?)";
        try {
            ps = dbWorker.getConnection().prepareStatement(addCustomer);
            ps.setInt(1, maxNumberCustomerInDerby());
            ps.setString(2, customer.getName_customer());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCustomerShopList().add(customer);
    }


    @Override
    public void takeAllCustomer() {
        String select = "SELECT * FROM customer";
        try {
            ps = dbWorker.getConnection().prepareStatement(select);
            ps.execute();
            resultSet = ps.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                CustomerShop cs = new CustomerShop(id, name);
                getCustomerShopList().add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

//    @Override
//    public void removeCustomer(String name) {
//
//    }
//
//    @Override
//    public void updateCustomer() {
//
//    }
}
