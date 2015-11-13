package shop.report;

/**
 * Created by panasyuk on 04.11.2015.
 */
public interface Reports {
    public void selectProductsAccordingCustomer(String name_customer);
    public void selectCustomerAccordingProduct(String name_product);
    public void totalCash();

}

