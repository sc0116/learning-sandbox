package chapter02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(final String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }
}
