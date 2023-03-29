package chapter01.Price;

import chapter01.Movie;

public class ChildrenPrice extends Price {

    @Override
    public double getCharge(final int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
}
