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

import static org.springframework.util.StringUtils.hasText;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordNotEqualsEmail.Validator.class)
public @interface PasswordNotEqualsEmail {

    String message() default "Password equals Email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public static class Validator implements ConstraintValidator<PasswordNotEqualsEmail, Model> {

        private PasswordNotEqualsEmail annotaion;

        @Override
        public void initialize(final PasswordNotEqualsEmail annotaion) {
            this.annotaion = annotaion;
        }

        @Override
        public boolean isValid(final Model model, final ConstraintValidatorContext context) {
            final String email = model.getEmail();
            final String password = model.getPassword();
            if(!(hasText(email) && hasText(password))) {
                return true;
            }
            return FiledNameModifier.of(context)
                    .withMessage(annotaion.message())
                    .changeTo("password")
                    .valid(!email.equals(password));
        }

    }

    public static interface Model {
        String getEmail();
        String getPassword();
    }

}
