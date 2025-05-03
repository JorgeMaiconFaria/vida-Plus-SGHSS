package com.vidaplus.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                d1 += digito * (10 - i);
                d2 += digito * (11 - i);
            }

            int resto1 = (d1 * 10) % 11;
            resto1 = (resto1 == 10) ? 0 : resto1;

            int resto2 = ((d2 + resto1 * 2) * 10) % 11;
            resto2 = (resto2 == 10) ? 0 : resto2;

            return resto1 == Character.getNumericValue(cpf.charAt(9)) &&
                    resto2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}
