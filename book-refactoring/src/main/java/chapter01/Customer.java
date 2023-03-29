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
        String result = getName() + " 고객님의 대여 기록\n";
        for (final Rental each : rentals) {
            // 이번에 대여하는 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }

        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(getTotalCharge()) + "\n";
        result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
        return result;
    }

    public String htmlStatement() {
        String result = "<H1><EM>" + getName() + " 고객님의 대여 기록</EM></H1><P>\n";
        for (final Rental each : rentals) {
            // 이번에 대여하는 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }

        // 푸터 행 추가
        result += "<P>누적 대여료: <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "적립 포인트: <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM><P>";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (final Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    public int getTotalFrequentRenterPoints() {
        int result = 0;
        for (final Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    public void addRental(final Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }
}
