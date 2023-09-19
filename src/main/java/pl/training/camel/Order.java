package pl.training.camel;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ";", crlf = "UNIX")
public class Order {

    @DataField(pos = 1)
    private String name;
    @DataField(pos = 2, precision = 2)
    private double price;
    @DataField(pos = 3)
    private boolean promoProduct;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPromoProduct() {
        return promoProduct;
    }

    public void setPromoProduct(boolean promoProduct) {
        this.promoProduct = promoProduct;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", promoProduct=" + promoProduct +
                '}';
    }

}
