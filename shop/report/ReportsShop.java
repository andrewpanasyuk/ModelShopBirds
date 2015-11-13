package shop.report;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 04.11.2015.
 */
public class ReportsShop implements Reports {
    private int sn;

    private String name_customer;
    private String name_bird;
    public Object [][] listReportNameCustomer;
    List<String> report = new ArrayList<>();

    public ReportsShop(){

    }



    @Override
    public void selectProductsAccordingCustomer(String name_customer) {
    }

    @Override
    public void selectCustomerAccordingProduct(String name_prodact) {
    }

    @Override
    public void totalCash() {

    }

    public List<String> getReport() {
        return report;
    }

    public void setReport(List<String> report) {
        this.report = report;
    }

    public String getName_bird() {
        return name_bird;
    }

    public void setName_bird(String name_bird) {
        this.name_bird = name_bird;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public Object[][] getListReportNameCustomer() {
        return listReportNameCustomer;
    }

    public void setListReportNameCustomer(Object[][] listReportNameCustomer) {
        this.listReportNameCustomer = listReportNameCustomer;
    }
}
