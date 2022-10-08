package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    private String oid;
    private LocalDate ordeDate;
    private String custId;

    private List<OrderDetailDTO> orderDetails;

    private String custName;
    private BigDecimal orderTotal;

    public OrderDTO() {
    }

    public OrderDTO(String oid, LocalDate ordeDate, String custId) {
        this.oid = oid;
        this.ordeDate = ordeDate;
        this.custId = custId;
    }

    public OrderDTO(String oid, LocalDate ordeDate, String custId, List<OrderDetailDTO> orderDetails) {
        this.oid = oid;
        this.ordeDate = ordeDate;
        this.custId = custId;
        this.orderDetails = orderDetails;
    }

    public OrderDTO(String oid, LocalDate ordeDate, String custId, String custName, BigDecimal orderTotal) {
        this.oid = oid;
        this.ordeDate = ordeDate;
        this.custId = custId;
        this.custName = custName;
        this.orderTotal = orderTotal;
    }

    public OrderDTO(String oid, String custId) {
        this.oid = oid;
        this.custId = custId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDate getOrdeDate() {
        return ordeDate;
    }

    public void setOrdeDate(LocalDate ordeDate) {
        this.ordeDate = ordeDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oid='" + oid + '\'' +
                ", ordeDate=" + ordeDate +
                ", custId='" + custId + '\'' +
                ", orderDetails=" + orderDetails +
                ", custName='" + custName + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
