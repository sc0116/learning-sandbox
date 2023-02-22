package chapter03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpirationDateCalculator {

    public LocalDate calculateExpirationDate(final PayData payData) {
        final int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private LocalDate expiryDateUsingFirstBillingDate(final PayData payData, final int addedMonths) {
        final LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if (isSameDayOfMonth(payData.getBillingDate(), candidateExp)) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(final LocalDate date1, final LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(final LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
