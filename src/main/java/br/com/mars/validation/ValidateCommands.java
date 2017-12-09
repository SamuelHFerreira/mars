package br.com.mars.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.mars.domain.constant.Command;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CommandValidator.class})
public @interface ValidateCommands {
    Class<?>[] groups() default {};

    String message() default "Not valid command";

    Class<? extends Payload>[] payload() default {};

    Command[] possibleCommands() default {};
}