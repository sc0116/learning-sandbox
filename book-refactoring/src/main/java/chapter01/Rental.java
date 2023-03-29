package chapter01;

public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(final Movie movie, final int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getFrequentRenterPoints() {
        // 최신물을 이틀 이상 대여하면 2포인트 지급하고 그 외엔 1포인트 지급
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }

    public double getCharge() {
        double result = 0;

        // 비디오 종류별 대여료 계산
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDaysRented() > 2) {
                    result += (getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) {
                    result += (getDaysRented() - 3) * 1.5;
                }
                break;
        }

        return result;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
