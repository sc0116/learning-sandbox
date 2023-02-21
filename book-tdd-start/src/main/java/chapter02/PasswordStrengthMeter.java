package chapter02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(final String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        int metCounts = getMetCriteriaCounts(s);

        if (metCounts <= 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(final String s) {
        int metCounts = 0;

        if (s.length() >= 8) metCounts++;
        if (meetsContainingNumberCriteria(s)) metCounts++;
        if (meetsContainingUppercaseCriteria(s)) metCounts++;

        return metCounts;
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
