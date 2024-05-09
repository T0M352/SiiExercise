package tomasz.morgas.siiZadanie.entity;

public class SalesRaport {
    private String currency;
    private double totalAmonut;
    private double totalDiscount;
    private int noOfPurchases;

    public SalesRaport() {
    }

    public SalesRaport(String currency, double totalAmonut, double totalDiscount, int noOfPurchases) {
        this.currency = currency;
        this.totalAmonut = totalAmonut;
        this.totalDiscount = totalDiscount;
        this.noOfPurchases = noOfPurchases;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getTotalAmonut() {
        return totalAmonut;
    }

    public void setTotalAmonut(double totalAmonut) {
        this.totalAmonut = totalAmonut;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public int getNoOfPurchases() {
        return noOfPurchases;
    }

    public void setNoOfPurchases(int noOfPurchases) {
        this.noOfPurchases = noOfPurchases;
    }
}
