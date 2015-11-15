package shop.customer;

import connectDB.DataSource;

import java.sql.*;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class CustomerDB extends CustomerShop {
    PreparedStatement ps;
    ResultSet resultSet;
    DataSource dataSource;
    Connection connection;

    public CustomerDB(DataSource dataSource) {
        this.dataSource = dataSource;
        takeAllCustomer();
    }

    public int maxNumberCustomerInDerby() {
        connection = dataSource.getConnections();
        int maxNumber = 0;
        String start = "SELECT MAX(id) AS id FROM customer";
        Statement s = null;
        try {
            s = connection.createStatement();
            s.execute(start);
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                maxNumber = rs.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setId_customer(maxNumber);
        setId(maxNumber);
        dataSource.takeConnect(connection);
        return maxNumber;
    }

    @Override
    public void addNewCustomer(String name) {
        connection = dataSource.getConnections();
        CustomerShop customer = new CustomerShop(name);
        String addCustomer = "INSERT INTO customer (id, name) values (?, ?)";
        try {
            ps = connection.prepareStatement(addCustomer);
            ps.setInt(1, maxNumberCustomerInDerby());
            ps.setString(2, customer.getName_customer());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCustomerShopList().add(customer);
        dataSource.takeConnect(connection);
    }


    @Override
    public void takeAllCustomer() {
        connection = dataSource.getConnections();
        String select = "SELECT * FROM customer";
        try {
            ps = connection.prepareStatement(select);
            ps.execute();
            resultSet = ps.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                CustomerShop cs = new CustomerShop(id, name);
                getCustomerShopList().add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.takeConnect(connection);

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
