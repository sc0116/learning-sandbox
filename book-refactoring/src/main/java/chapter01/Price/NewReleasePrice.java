package chapter01.Price;

import chapter01.Movie;

public class NewReleasePrice extends Price {

    @Override
    public int getFrequentRenterPoints(final int daysRented) {
        if (daysRented > 1) {
            return 2;
        }
        return 1;
    }

    @Override
    public double getCharge(final int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
}
