package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDetailDTO implements Serializable {

    private String oid;
    private String itemCode;
    private int orderQty;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private double total;
    private LocalDate date;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String itemCode, int orderQty, BigDecimal unitPrice, BigDecimal discount, double total, LocalDate date) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.total = total;
        this.date = date;
    }

    public OrderDetailDTO(String oid, String itemCode, int orderQty, BigDecimal discount) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public OrderDetailDTO(String oid, String itemCode, int orderQty, BigDecimal unitPrice, BigDecimal discount, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailDTO(BigDecimal unitPrice, int orderQty, BigDecimal discount, LocalDate date,  double total) {
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
        this.discount = discount;
        this.date = date;
        this.total = total;
    }



    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                ", discount=" + discount +
                '}';
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
