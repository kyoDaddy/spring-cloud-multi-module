package com.mall.gateway.process.login.validator;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordEqualsConfirmPassword.Validator.class)
public @interface PasswordEqualsConfirmPassword {

    String message() default "Password not equals Confirm password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public static class Validator implements ConstraintValidator<PasswordEqualsConfirmPassword, Model> {

        private PasswordEqualsConfirmPassword annotation;

        @Override
        public void initialize(final PasswordEqualsConfirmPassword annotation) {
            this.annotation = annotation;
        }

        @Override
        public boolean isValid(final Model model, final ConstraintValidatorContext context) {
            String password = model.getPassword();
            String confirmPassword = model.getConfirmPassword();
            if(password == null) {
                password = "";
            }
            if(confirmPassword == null) {
                confirmPassword = "";
            }
            return FiledNameModifier.of(context)
                    .withMessage(annotation.message())
                    .changeTo("confirmPassword")
                    .valid(password.equals(confirmPassword));
        }

    }

    public static interface Model {
        String getPassword();
        String getConfirmPassword();
    }

}
