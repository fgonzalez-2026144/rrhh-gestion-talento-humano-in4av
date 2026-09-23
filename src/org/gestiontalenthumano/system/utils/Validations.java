package org.gestiontalentoshumanos.system.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validations {

    private static final String NAME_REGEX = "^[\\p{L}][\\p{L}.'\\-]*(\\s+[\\p{L}][\\p{L}.'\\-]*)+$";
    private static final String SALARY_REGEX = "^\\d{1,8}(\\.\\d{1,2})?$";
    private static final String ID_REGEX = "^\\d{1,9}$";

    private Validations() {
    }

    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmployeeId(String value) {
        return isNotBlank(value) && value.trim().matches(ID_REGEX) && Integer.parseInt(value.trim()) > 0;
    }

    public static boolean isValidFullName(String value) {
        return isNotBlank(value) && value.trim().length() <= 100 && value.trim().matches(NAME_REGEX);
    }

    public static boolean isValidSalary(String value) {
        return isNotBlank(value) && value.trim().matches(SALARY_REGEX) && parseSalary(value).compareTo(BigDecimal.ZERO) > 0;
    }

    public static BigDecimal parseSalary(String value) {
        return new BigDecimal(value.trim());
    }

    public static boolean isValidHireDate(LocalDate date) {
        return date != null && date.getYear() >= 1950;
    }
}
