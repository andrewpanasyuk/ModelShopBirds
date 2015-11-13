package shop.transaction;

import com.mysql.jdbc.*;
import connectDB.DBWorker;

import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by panasyuk on 02.11.2015.
 */
public class TransactionDb extends TransactionShop {
    private DBWorker dbWorker;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private CallableStatement cs;

    public TransactionDb(DBWorker dbWorker) {
        this.dbWorker = dbWorker;
    }

    @Override
    public int createTransaction(String name_customer, String name_bird, int qnt) {
        int numberTr = lastID();
        int totalProdOnStock = controlStock(name_bird, qnt);
        int idCustomer = getIdCustomer(name_customer);
        if (totalProdOnStock - qnt < 0) {
            return 0;
        }
        String newTransaction = "INSERT INTO transactions (id_transaction, fk_customer_id, qnt_birds) VALUES (?, ?, ?)";
        try {
            cs = dbWorker.getConnection().prepareCall(newTransaction);
            cs.setInt(1, numberTr);
            cs.setInt(2, idCustomer);
            cs.setInt(3, qnt);
            cs.execute();
            int a = updateStock(name_bird, totalProdOnStock - qnt);

            createST(numberTr, a);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 1;
    }

    public int lastID() {
        int lastID = 0;
        String start = "SELECT MAX(id_transaction) AS id_transaction FROM transactions";
        Statement s = null;
        try {
            s = dbWorker.getConnection().createStatement();
            s.execute(start);
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                lastID = rs.getInt("id_transaction");
                System.out.println(rs.getInt("id_transaction") + " *-*--*-*-*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(lastID);
        return lastID + 1;
    }

    public int updateStock(String name_bird, int rezult) {
        String update = "UPDATE stock_shop SET qnt = ? WHERE name_prodact = ?";
        String idProd = "SELECT id_prodact FROM stock_shop WHERE name_prodact = ?";
        int prodID = 0;
        try {
            cs = dbWorker.getConnection().prepareCall(update);
            cs.setInt(1, rezult);
            cs.setString(2, name_bird);
            cs.execute();
            cs.close();

            cs = dbWorker.getConnection().prepareCall(idProd);
            cs.setString(1, name_bird);
            cs.execute();
            resultSet = cs.getResultSet();
            while (resultSet.next()) {
                prodID = resultSet.getInt("id_prodact");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodID;
    }

    public void createST(int transaction, int prod) {
        String update = "INSERT INTO secondary_transaction (fk_transaction_id, fk_podact_id) VALUES (?, ?)";
        try {
            cs = dbWorker.getConnection().prepareCall(update);
            cs.setInt(1, transaction);
            cs.setInt(2, prod);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdCustomer(String name_customer) {
        String howManyBirdOnStock = "SELECT id FROM customer WHERE name = ?";
        try {
            cs = dbWorker.getConnection().prepareCall(howManyBirdOnStock);
            cs.setString(1, name_customer);
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int controlStock(String name_bird, int qnt) {
        String howManyBirdOnStock = "SELECT qnt FROM stock_shop WHERE name_prodact = ?";
        try {
            cs = dbWorker.getConnection().prepareCall(howManyBirdOnStock);
            cs.setString(1, name_bird);
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            while (resultSet.next()) {
                return resultSet.getInt("qnt");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public void takeAllTransaction() {
        getTransactionShopList().clear();
        String allTransaction = "SELECT * from customer JOIN transactions on (transactions.fk_customer_id = customer.id) JOIN secondary_transaction on (transactions.id_transaction = secondary_transaction.fk_transaction_id) JOIN stock_shop on (secondary_transaction.fk_podact_id = stock_shop.id_prodact)";
        try {
            Statement st = dbWorker.getConnection().createStatement();
            st.execute(allTransaction);
            resultSet = st.getResultSet();
            int i = 0;
            while (resultSet.next()) {
                System.out.println(i + " ++");
                int id = resultSet.getInt("id_transaction");

                java.util.Date data = resultSet.getDate("data");
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm ");
                String text = sdf.format(data);

                String name_customer = resultSet.getString("name");

                String name_bird = resultSet.getString("name_prodact");
                int qnt = resultSet.getInt("qnt_birds");
                getTransactionShopList().add(new TransactionShop(id, text, name_customer, name_bird, qnt));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    ***********************      example the code for calling a procedure   ******************
//
//    @Override
//    public int createTransaction(String name_customer, String name_bird, int qnt) {
//        int a = 1;
//        try {
//            CallableStatement callableStatement = dbWorker.getConnection().prepareCall("{call chek_stock(?, ?, ?, ?, ?)}");
//            callableStatement.setInt(1, getIdTransaction());
//
//            callableStatement.setString(2, name_bird);
//            callableStatement.setInt(3, qnt);
//            callableStatement.setString(4, name_customer);
//
//            callableStatement.registerOutParameter(5, Types.INTEGER);
//            callableStatement.execute();
//            a = callableStatement.getInt(4);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return a;
////        super.createTransaction(name_customer, name_bird, qnt);
//    }


//    ****************************  text PROCEDURE  **************************************
//
//            "CREATE PROCEDURE `chek_stock`(IN name_bird varchar(40), IN qnt_order int, IN name_cust varchar(40), OUT rezult int)\n"+
//            "BEGIN\n"+
//            "declare prod_on_stock int;\n"+
//            "SELECT qnt FROM stock_shop where name_prodact = name_bird into prod_on_stock;\n"+
//            "if (select prod_on_stock >= qnt_order) then set rezult = 1;\n"+
//            "call newTransaction(name_cust, name_bird, qnt_order);\n"+
//            "else set rezult = 0;\n"+
//            "end if;\n"+
//            "END";


}
