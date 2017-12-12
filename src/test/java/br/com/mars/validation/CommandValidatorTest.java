package br.com.mars.validation;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.lang.annotation.Annotation;

import javax.validation.Payload;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.mars.domain.constant.Command;

public class CommandValidatorTest {

    private final static Command[] COMMANDS_ARRAY = Command.values();

    CommandValidator commandValidator = new CommandValidator();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setupCommands() {
        commandValidator.initialize(new ValidateCommands() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public String message() {
                return null;
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Command[] possibleCommands() {
                return COMMANDS_ARRAY;
            }
        });
    }

    @Test
    public void onValidCommandStringTest() {
        String validCommand = Command.M.name();
        assertTrue(commandValidator.isValid(validCommand, null));
    }

    @Test
    public void onInvalidCommandStringTest() {
        String invalidCommand = "s";
        assertFalse(commandValidator.isValid(invalidCommand, null));
    }

    @Test
    public void onInvalidLongCommandStringTest() {
        String validCommand = "MMMMMS";
        assertFalse(commandValidator.isValid(validCommand, null));
    }
}