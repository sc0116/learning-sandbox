package chapter01;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(final String name) {
        this.name = name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = getName() + " 고객님의 대여 기록\n";

        for (final Rental each : rentals) {
            // 적립 포인트를 1 포인트 증가
            frequentRenterPoints++;
            // 최신물을 이틀 이상 대여하면 보너스 포인트 지급
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1
            ) {
                frequentRenterPoints++;
            }

            // 이번에 대여한느 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
            // 현재까지 누적된 총 대여료
            totalAmount += each.getCharge();
            // 푸터 행 추가
        }

        result += "누적 대여료: " + String.valueOf(totalAmount) + "\n";
        result += "적립 포인트: " + String.valueOf(frequentRenterPoints);
        return result;
    }

    public void addRental(final Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }
}
