package chapter01.Price;

public abstract class Price {

    public int getFrequentRenterPoints(final int daysRented) {
        return 1;
    }

    public abstract double getCharge(final int daysRented);

    public abstract int getPriceCode();
}
