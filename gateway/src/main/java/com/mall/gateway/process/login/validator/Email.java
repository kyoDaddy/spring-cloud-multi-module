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
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.hasText;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Email.Validator.class)
public @interface Email {

    String message() default "invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    public class Validator implements ConstraintValidator<Email, String> {

        private static final String REGEXP = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        @Override
        public void initialize(Email annotation) {

        }

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            if(!hasText(email)) {
                return true;
            }
            return Pattern.compile(REGEXP).matcher(email).matches();
        }

        public static String getEmailRegExp() {
            return REGEXP;
        }

    }

}
