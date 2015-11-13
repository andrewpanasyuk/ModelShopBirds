package gui;

import shop.Shop;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class TableMaker extends JPanel{
    private Shop shop;
    public TableMaker(){

    }
    public TableMaker(Shop shop){
        this.shop = shop;
    }




    public JScrollPane makeTabl(String typeTable) {
        JTable startPanel = new JTable();
        return new JScrollPane(startPanel);

    }
    public JTable reportTable(){
        Object[] colum = new Object[shop.getReportsShop().getListReportNameCustomer().length];
        for (int i  = 0; i < colum.length; i ++){
             colum [i] = shop.getReportsShop().getListReportNameCustomer()[i][0];
        }
        Object [][] row = new Object[shop.getReportsShop().getListReportNameCustomer()[0].length-1][colum.length];

        for (int i = 0; i < shop.getReportsShop().getListReportNameCustomer()[0].length-1; i++) {
            row[i][0] = shop.getReportsShop().getListReportNameCustomer()[0][i+1];
            row[i][1] = shop.getReportsShop().getListReportNameCustomer()[1][i+1];
            row[i][2] = shop.getReportsShop().getListReportNameCustomer()[2][i+1];
            row[i][3] = shop.getReportsShop().getListReportNameCustomer()[3][i+1];
        }

        JTable table = new JTable(row, colum);
        TableColumn column = null;
        for (int i = 0; i < colum.length; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(5);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else {
                column.setPreferredWidth(50);
            }
        }

        return table;
    }


    public JTable stockTable() {
        String[] colum = new String[]{"ID prod.", "Bird's name", "in stock", "Price"};
        String[][] row = new String[shop.getStock().getStockList().size()][colum.length];
        for (int i = 0; i < shop.getStock().getStockList().size(); i++) {
            row[i][0] = Integer.toString(shop.getStock().getStockList().get(i).getId());
            row[i][1] = shop.getStock().getStockList().get(i).getBird();
            row[i][2] = Integer.toString(shop.getStock().getStockList().get(i).getOnStock());
            row[i][3] = Double.toString(shop.getStock().getStockList().get(i).getPrice());
        }
        JTable table = new JTable(row, colum);
        TableColumn column = null;
        for (int i = 0; i < colum.length; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else {
                column.setPreferredWidth(50);
            }
        }

        return table;
    }

    public JTable listTransaction() {
        shop.getTransactionShop().takeAllTransaction();

        String[] colum = new String[]{"ID transaction", "Data", "Name customer", "Name bird", "Qnt."};
        String[][] row = new String[shop.getTransactionShop().getTransactionShopList().size()][colum.length];
        for (int i = 0; i < shop.getTransactionShop().getTransactionShopList().size(); i++) {
            row[i][0] = Integer.toString(shop.getTransactionShop().getTransactionShopList().get(i).getCurrentID());
            row[i][1] = (shop.getTransactionShop().getTransactionShopList().get(i).getData()).toString();
            row[i][2] = shop.getTransactionShop().getTransactionShopList().get(i).getName_customer();
            row[i][3] = shop.getTransactionShop().getTransactionShopList().get(i).getName_bird();
            row[i][4] = Integer.toString(shop.getTransactionShop().getTransactionShopList().get(i).getQnt());
        }
        JTable table = new JTable(row, colum);
        TableColumn column = null;
        for (int i = 0; i < colum.length; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else {
                column.setPreferredWidth(50);
            }
        }

        return table;
    }

}
