package chapter02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(final String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        if (!meetsContainingNumberCriteria(s)) {
            return PasswordStrength.NORMAL;
        }

        if (!meetsContainingUppercaseCriteria(s)) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(final String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(final String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
