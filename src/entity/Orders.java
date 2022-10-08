package entity;

import java.sql.Date;
import java.time.LocalDate;

public class Orders {
    private String oid;
    private LocalDate ordeDate;
    private String custId;

    public Orders() {
    }

    public Orders(String oid, LocalDate ordeDate, String custId) {
        this.oid = oid;
        this.ordeDate = ordeDate;
        this.custId = custId;
    }

    public Orders(String oid, String custId) {
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
}
