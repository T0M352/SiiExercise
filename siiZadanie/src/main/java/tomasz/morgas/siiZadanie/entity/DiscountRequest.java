package tomasz.morgas.siiZadanie.entity;

public class DiscountRequest {
    private String promoCode;
    private String productName;

    public DiscountRequest(String promoCode, String productName) {
        this.promoCode = promoCode;
        this.productName = productName;
    }

    public DiscountRequest() {
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
