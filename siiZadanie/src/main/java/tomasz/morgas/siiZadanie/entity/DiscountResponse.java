package tomasz.morgas.siiZadanie.entity;

public class DiscountResponse {
    private String message;
    private double price;

    public DiscountResponse() {
    }

    public DiscountResponse(String message, double price) {
        this.message = message;
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
