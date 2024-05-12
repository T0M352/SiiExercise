package tomasz.morgas.siiZadanie.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="product")
public class Product{

    @Id
    @Column(name="productName")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="regular_price")
    private double regularPrice;

    @Column(name="currency")
    private String currency;

    public Product() {
    }

    public Product(String name, String description, double regularPrice, String currency) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Product{" +
//                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", regularPrice=" + regularPrice +
                ", currency='" + currency + '\'' +
                '}';
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
