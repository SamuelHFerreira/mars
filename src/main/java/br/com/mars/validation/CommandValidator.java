package br.com.mars.validation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.mars.domain.constant.Command;

public class CommandValidator implements ConstraintValidator<ValidateCommands, String> {

    List<Command> validCommands;

    @Override
    public void initialize(ValidateCommands validateCommands) {
        this.validCommands = validateCommands.possibleCommands().length == 0 ?
                Arrays.asList(Command.values()) :
                Arrays.asList(validateCommands.possibleCommands());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> commands = Pattern.compile("").splitAsStream(value).collect(Collectors.toList());
        return !hasAnyInvalidCommand(commands);
    }

    private boolean hasAnyInvalidCommand(List<String> commands) {
        return commands.stream().filter(command -> isInvalidCommand(command))
                .findAny()
                .isPresent();
    }

    private boolean isInvalidCommand(String command) {
        return !validCommands.stream().filter(validCommand -> validCommand.name()
                .equalsIgnoreCase(command)).findAny().isPresent();
    }
}