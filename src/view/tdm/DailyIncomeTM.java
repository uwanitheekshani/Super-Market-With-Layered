package view.tdm;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyIncomeTM {
    private LocalDate ordeDate;
    private BigDecimal unitPrice;


    public DailyIncomeTM() {
    }

    public DailyIncomeTM(LocalDate ordeDate, BigDecimal unitPrice) {
        this.ordeDate = ordeDate;
        this.unitPrice = unitPrice;
    }

    public LocalDate getOrdeDate() {
        return ordeDate;
    }

    public void setOrdeDate(LocalDate ordeDate) {
        this.ordeDate = ordeDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "DailyIncomeTM{" +
                "ordeDate=" + ordeDate +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
