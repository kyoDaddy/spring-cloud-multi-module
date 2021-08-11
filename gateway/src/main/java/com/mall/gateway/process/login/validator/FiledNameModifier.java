package com.mall.gateway.process.login.validator;

import javax.validation.ConstraintValidatorContext;

public class FiledNameModifier {

    private final ConstraintValidatorContext context;

    private String fieldName;

    private String message;

    private FiledNameModifier(final ConstraintValidatorContext context) {
        this.context = context;
    }

    public static FiledNameModifier of(final ConstraintValidatorContext context) {
        return new FiledNameModifier(context);
    }

    public FiledNameModifier changeTo(final String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public FiledNameModifier withMessage(final String message) {
        this.message = message;
        return this;
    }

    public boolean valid(final boolean isValid) {
        if(isValid) {
            return true;
        }

        if(context != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode(this.fieldName)
                    .addConstraintViolation();
        }
        return false;
    }

}
