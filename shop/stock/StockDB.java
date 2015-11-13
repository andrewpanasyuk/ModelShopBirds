package shop.stock;

import connectDB.DBWorker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class StockDB extends StockShop {
    private DBWorker dbWorker;
    PreparedStatement ps;
    ResultSet resultSet;

    public StockDB (DBWorker dbWorker){
        this.dbWorker = dbWorker;
        setId(getId()+1);
    }


    @Override
    public void addNewTypeProducts(int id, String bird_name, int qnt, double price) {
        if (!controlType(bird_name)) {
            String addNewPos = "INSERT into stock_shop (id_prodact, name_prodact, qnt, price_prodact)  values(?, ?, ?, ?)";
            try {
                ps = dbWorker.getConnection().prepareStatement(addNewPos);
                ps.setInt(1, id);
                ps.setString(2, bird_name);
                ps.setInt(3, qnt);
                ps.setDouble(4, price);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            takeCurrentStock();
        }

    }

    public boolean controlType(String name) {
        String controlName = "SELECT id_prodact FROM stock_shop WHERE name_prodact = ? ";
        try {
            ps = dbWorker.getConnection().prepareStatement(controlName);
            ps.setString(1, name);
            ps.execute();
            resultSet = ps.getResultSet();
            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void updatePosition(String wot, String name, String parametr) {
        String update = "UPDATE stock_shop SET " + wot + " = ? WHERE name_prodact = ?";
        try {
            ps = dbWorker.getConnection().prepareStatement(update);
            ps.setString(2, name);
            if (wot.equals("price_prodact")){
                ps.setDouble(1,Double.valueOf(parametr));
            } else {
                ps.setInt(1, Integer.valueOf(parametr));
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void takeCurrentStock() {
        setStockList(new ArrayList<>());
        String stock = "SELECT * FROM stock_shop";
        try {
            ps = dbWorker.getConnection().prepareStatement(stock);
            ps.execute();
            resultSet = ps.getResultSet();
            while (resultSet.next()){
                int id_s = resultSet.getInt("id_prodact");
                String name_s = resultSet.getString("name_prodact");
                int onStock_s = resultSet.getInt("qnt");
                double price_s = resultSet.getDouble("price_prodact");
                StockShop st = new StockShop(id_s, name_s, onStock_s, price_s);
                getStockList().add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
