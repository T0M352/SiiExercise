package tomasz.morgas.siiZadanie.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name ="purchase_information")
public class PurchaseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="date")
    private LocalDate date;
    @Column(name="regular_price")
    private double regularPrice;
    @Column(name="discount_amount")
    private double discountAmount;
    @Column(name="product_name")
    private String productName;

    @Column(name="currency")
    private String currency;

    public PurchaseInformation(LocalDate date, double regularPrice, double discountAmount, String productName, String currency) {
        this.date = date;
        this.regularPrice = regularPrice;
        this.discountAmount = discountAmount;
        this.productName = productName;
        this.currency = currency;
    }

    public PurchaseInformation() {
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
