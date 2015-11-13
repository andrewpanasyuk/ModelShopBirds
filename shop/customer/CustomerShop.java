package shop.customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 01.11.2015.
 */
public class CustomerShop implements CustomerService {
    private static int id = 0;

    private int id_customer;
    private String name_customer;
    private List<CustomerShop> customerShopList = new ArrayList<>();

    public CustomerShop(String name_customer){
        id++;
        id_customer = id;
       this.name_customer = name_customer;
    }

    @Override
    public void addNewCustomer(String name) {
        customerShopList.add(new CustomerShop(name));



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

    @Override
    public void takeAllCustomer() {

    }


    public CustomerShop(){

    }
    public CustomerShop(int id_customer, String name_customer){

        this.id_customer = id_customer;
        this.name_customer = name_customer;

    }

    public static void setId(int id) {
        CustomerShop.id = id;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public List<CustomerShop> getCustomerShopList() {
        return customerShopList;
    }

    public void setCustomerShopList(List<CustomerShop> customerShopList) {
        this.customerShopList = customerShopList;
    }

    public int getId_customer() {
        return id_customer;
    }


    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }
}
