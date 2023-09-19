package pl.training.camel;

import jakarta.persistence.*;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ";", crlf = "UNIX")
@Table(name = "orders")
@Entity
public class Order {

    @GeneratedValue
    @Id
    private Long id;
    @DataField(pos = 1)
    private String name;
    @DataField(pos = 2, precision = 2)
    private String price;
    @DataField(pos = 3)
    private boolean promoProduct;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", promoProduct=" + promoProduct +
                '}';
    }
}
