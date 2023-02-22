package chapter03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    private PayData() {}

    public PayData(final LocalDate billingDate, final int payAmount) {
        this(null, billingDate, payAmount);
    }

    public PayData(final LocalDate firstBillingDate, final LocalDate billingDate, final int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }
}
