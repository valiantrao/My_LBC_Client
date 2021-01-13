package valiant.mylbcclient.model;

public class BuyAdList {

    private String name;
    private String last_online;
    private String price;
    private String limit;
    private String payment_window;
    private String payment_method;

    public BuyAdList(String name, String last_online, String price, String limit, String payment_window, String payment_method) {
        this.name = name;
        this.last_online = last_online;
        this.price = price;
        this.limit = limit;
        this.payment_window = payment_window;
        this.payment_method = payment_method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_online() {
        return last_online;
    }

    public void setLast_online(String last_online) {
        this.last_online = last_online;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPayment_window() {
        return payment_window;
    }

    public void setPayment_window(String payment_window) {
        this.payment_window = payment_window;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}