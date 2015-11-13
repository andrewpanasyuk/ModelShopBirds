package shop.customer;

import shop.TestData;

/**
 * Created by panasyuk on 06.11.2015.
 */
public class CustomerL extends CustomerShop {
    public CustomerL(){
        TestData td = new TestData();
        setCustomerShopList(td.creatTestCustomer());

    }

    @Override
    public void addNewCustomer(String name) {
        getCustomerShopList().add(new CustomerShop(name));
    }
}
