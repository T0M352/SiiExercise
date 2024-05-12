package tomasz.morgas.siiZadanie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Table(name ="discount")
public class Discount {
    @Id
    @Column(name="promo_code")
    @Size(min = 3, max = 24, message = "Code should be 3-24 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Code should contain only alphanumeric characters")
    private String promoCode;

    @Column(name="type")
    @Range(min = 0, max = 1, message = "Type of discountcode should be 0 or 1")
    private int type;


    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="discount_amount")
    private double discountAmount;

    @Column(name="currency")
    private String currency;

    @Column(name="max_usages")
    private int maxUsages;

    @Column(name="current_usages")
    private int currentUsages;


    public Discount(LocalDate expirationDate, double discountAmount, String currency, int maxUsages, int currentUsages, int type) {
        this.expirationDate = expirationDate;
        this.discountAmount = discountAmount;
        this.currency = currency;
        this.maxUsages = maxUsages;
        this.currentUsages = currentUsages;
        this.type = type;
    }

    public Discount() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMaxUsages() {
        return maxUsages;
    }

    public void setMaxUsages(int maxUsages) {
        this.maxUsages = maxUsages;
    }

    public int getCurrentUsages() {
        return currentUsages;
    }

    public void setCurrentUsages(int currentUsages) {
        this.currentUsages = currentUsages;
    }
}
