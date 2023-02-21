package chapter02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(final String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        final boolean lengthEnough = s.length() >= 8;
        final boolean containsNum = meetsContainingNumberCriteria(s);
        final boolean containsUpp = meetsContainingUppercaseCriteria(s);

        if (lengthEnough && !containsNum && !containsUpp) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && containsNum && !containsUpp) {
            return PasswordStrength.WEAK;
        }

        if (!lengthEnough) {
            return PasswordStrength.NORMAL;
        }
        if (!containsNum) {
            return PasswordStrength.NORMAL;
        }
        if (!containsUpp) {
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
