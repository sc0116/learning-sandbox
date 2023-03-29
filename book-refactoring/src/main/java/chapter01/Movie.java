package chapter01;

import chapter01.Price.ChildrenPrice;
import chapter01.Price.NewReleasePrice;
import chapter01.Price.Price;
import chapter01.Price.RegularPrice;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(final String title, final int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getFrequentRenterPoints(final int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

    public double getCharge(final int daysRented) {
        return price.getCharge(daysRented);
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(final int priceCode) {
        switch (priceCode) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrenPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("가격 코드가 잘못됐습니다.");
        }
    }
}
