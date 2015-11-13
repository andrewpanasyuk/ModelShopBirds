package shop.report;

import shop.stock.StockShop;
import shop.transaction.TransactionShop;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by panasyuk on 08.11.2015.
 */
public class ReportC extends ReportsShop {
    private TransactionShop transactionShop;
    private StockShop stockShop;

    public ReportC(TransactionShop transactionShop, StockShop stockShop) {
        this.transactionShop = transactionShop;
        this.stockShop = stockShop;

    }

    @Override
    public void selectCustomerAccordingProduct(String name_product) {
        HashMap<String, Integer> totalBirdsInCustomer = new HashMap<>();

        for (int i = 0; i < transactionShop.getTransactionShopList().size(); i++) {
            if (transactionShop.getTransactionShopList().get(i).getName_bird().equals(name_product)) {
                if (totalBirdsInCustomer.containsKey(transactionShop.getTransactionShopList().get(i).getName_customer())) {
                    totalBirdsInCustomer.replace(transactionShop.getTransactionShopList().get(i).getName_customer(), totalBirdsInCustomer.get(transactionShop.getTransactionShopList().get(i).getName_customer()), totalBirdsInCustomer.get(transactionShop.getTransactionShopList().get(i).getName_customer()) + transactionShop.getTransactionShopList().get(i).getQnt());

                } else {
                    totalBirdsInCustomer.put(transactionShop.getTransactionShopList().get(i).getName_customer(), transactionShop.getTransactionShopList().get(i).getQnt());
                }
            }
        }
        setListReportNameCustomer(new Object[4][totalBirdsInCustomer.size() + 1]);
        listReportNameCustomer[0][0] = "#";
        listReportNameCustomer[1][0] = "Customer";
        listReportNameCustomer[2][0] = "Total qnt.";
        listReportNameCustomer[3][0] = "Total money";
        int n = 1;

        Set<String> key_customer = totalBirdsInCustomer.keySet();
        Object [] s = key_customer.toArray();


        for (int i = 0; i < totalBirdsInCustomer.size(); i++) {
            listReportNameCustomer[0][i+1] = i + 1;
            listReportNameCustomer[1][i+1] = s[i];
            listReportNameCustomer[2][i+1] = totalBirdsInCustomer.get(s[i]);
            for (int k = 0; k < stockShop.getStockList().size(); k++){
                if (stockShop.getStockList().get(k).getBird().equals(name_product)){
                    listReportNameCustomer[3][i+1] = (Integer) listReportNameCustomer[2][i+1] * stockShop.getStockList().get(k).getPrice();
                }
            }

        }

    }


    @Override
    public void selectProductsAccordingCustomer(String name_customer) {


        setListReportNameCustomer(new Object[4][2]);
        listReportNameCustomer[0][0] = "#";
        listReportNameCustomer[1][0] = "Name Customer";
        listReportNameCustomer[2][0] = "qnt transactions";
        listReportNameCustomer[3][0] = "Total money";
        int qnt_t = 0;
        double money = 0;


        for (int i = 0; i < transactionShop.getTransactionShopList().size(); i++) {
            if (transactionShop.getTransactionShopList().get(i).getName_customer().equals(name_customer)) {
                qnt_t++;
                int n = transactionShop.getTransactionShopList().get(i).getQnt();
                String b = transactionShop.getTransactionShopList().get(i).getName_bird();
                for (int a = 0; a < stockShop.getStockList().size(); a++) {
                    if (stockShop.getStockList().get(a).getBird().equals(b)) {
                        money = money + stockShop.getStockList().get(a).getPrice() * n;
                    }
                }

            }
        }
        if (name_customer.equals("-- ALL CUSTOMERS: --")) {
            for (int i = 0; i < transactionShop.getTransactionShopList().size(); i++) {
                qnt_t++;
                int n = transactionShop.getTransactionShopList().get(i).getQnt();
                String b = transactionShop.getTransactionShopList().get(i).getName_bird();
                for (int a = 0; a < stockShop.getStockList().size(); a++) {
                    if (stockShop.getStockList().get(a).getBird().equals(b)) {
                        money = money + stockShop.getStockList().get(a).getPrice() * n;
                    }
                }
            }

        }
        listReportNameCustomer[0][1] = 1;
        listReportNameCustomer[1][1] = name_customer;
        listReportNameCustomer[2][1] = qnt_t;
        listReportNameCustomer[3][1] = money;
    }


}
