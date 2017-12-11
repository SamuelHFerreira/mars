package br.com.mars.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.component.action.Action;
import br.com.mars.component.action.ActionCoordinator;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

@Service
public class WalkService {

    @Autowired ActionCoordinator actionCoordinator;

    public Position followCommands(final List<Command> commands) {
        List<Action> actions = commands.stream().map(command -> getCommandAction(command)).collect
                (Collectors.toList());
        actions.stream().forEach(action -> actionCoordinator.addAction(action));
        return actionCoordinator.run();
    }

    private Action getCommandAction(final Command command) {
        try {
            return (Action) command.getAction().getDeclaredConstructor(Command.class).newInstance(command);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}