package connectDB;

import java.sql.*;

/**
 * Created by panasyuk on 09.11.2015.
 */
public class CreateDerbyTabl {
    private DataSource dataSource;

    public CreateDerbyTabl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void CreateTabl() {
        Connection con = dataSource.getConnections();
        Statement st = null;

        // 1
        try {
            st = con.createStatement();
            String createCustomerTable = "CREATE TABLE customer (id INT NOT NULL , name VARCHAR(30) NOT NULL, PRIMARY KEY(id))";
            st.executeUpdate(createCustomerTable);
            st.close();

            System.out.println("Table CUSTOMER creation process successfully!");
        } catch (SQLException e) {
            System.out.println("Table CUSTOMER all ready exists!");
            String start = "SELECT MAX (id) AS id FROM customer";
            Statement s = null;
            try {
                s = con.createStatement();
                s.execute(start);
                ResultSet rs = s.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getInt("id"));
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        // 2
        try {
            st = con.createStatement();
            String createTransactionTable = "CREATE TABLE transactions (id_transaction INT NOT NULL, fk_customer_id INT NOT NULL, data TIMESTAMP DEFAULT CURRENT_TIMESTAMP, qnt_birds INT NOT NULL, PRIMARY KEY(id_transaction))";
            st.executeUpdate(createTransactionTable);
            st.close();
            System.out.println("Table TRANSACTION creation process successfully!");
        } catch (SQLException e) {

            System.out.println("Table TRANSACTION all ready exists!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 3
        try {
            st = con.createStatement();

            String createStockTable = "CREATE TABLE stock_shop (id_prodact INT NOT NULL, name_prodact VARCHAR(30), qnt INT, price_prodact DOUBLE, PRIMARY KEY(id_prodact))";
            st.executeUpdate(createStockTable);
            st.close();
            System.out.println("Table STOCK creation process successfully!");
        } catch (SQLException e) {
            System.out.println("Table STOCK all ready exists!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 4
        try {
            st = con.createStatement();


            String createSTTable = "CREATE TABLE secondary_transaction (fk_podact_id INT, fk_transaction_id INT)";
            st.executeUpdate(createSTTable);
            System.out.println("Table ST creation process successfully!");
        } catch (SQLException e) {
            System.out.println("Table ST all ready exists!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//         --
        dataSource.takeConnect(con);
    }

//    public void setStartParam() {
//
//        try {
//            String start = "SELECT MAX (id) AS id FROM customer";
//            Statement s = dataSource.getConnections().createStatement();
//            s.execute(start);
//            ResultSet rs = s.getResultSet();
//            while (rs.next()) {
//                System.out.println(rs.getInt("id"));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("--------------------");
//        }
//    }

    public void clear() {
        Connection con = dataSource.getConnections();
        Statement st = null;

        try {
            st = con.createStatement();
            String drop = "DROP TABLE customer";
            st.executeUpdate(drop);
            System.out.println("Table CUSTOMER deleted is successful!");
        } catch (SQLException e) {
            System.out.println("Table CUSTOMER didn't was is deleted!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 2
        try {
            st = con.createStatement();
            String drop = "DROP TABLE transactions";
            st.executeUpdate(drop);
            System.out.println("Table TRANSACTION deleted is successful!");
        } catch (SQLException e) {
            System.out.println("Table TRANSACTION didn't was is deleted!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 3
        try {
            st = con.createStatement();

            String drop = "DROP TABLE stock_shop";
            st.executeUpdate(drop);
            System.out.println("Table STOCK deleted is successful!");
        } catch (SQLException e) {
            System.out.println("Table STOCK didn't was is deleted!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 4
        try {
            st = con.createStatement();

            String drop = "DROP TABLE secondary_transaction";
            st.executeUpdate(drop);
            System.out.println("Table ST deleted is successful!");
        } catch (SQLException e) {
            System.out.println("Table ST didn't was is deleted!");
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//         --
    }
}
