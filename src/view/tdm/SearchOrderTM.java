package view.tdm;

public class SearchOrderTM {
    private String orderId;
    private String customerId;

    public SearchOrderTM() {
    }

    public SearchOrderTM(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "SearchOrderTM{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
