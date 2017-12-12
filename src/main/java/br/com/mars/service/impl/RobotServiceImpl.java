package br.com.mars.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.component.action.Action;
import br.com.mars.component.action.coordinator.Coordinator;
import br.com.mars.component.location.LocationManager;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;
import br.com.mars.service.RobotService;

@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    Coordinator coordinator;

    @Autowired
    LocationManager locationManager;

    @Override
    public Position followCommands(final List<Command> commands) throws MapViolationException {
        List<Action> actions = commands.stream().map(command -> getCommandAction(command)).collect
                (Collectors.toList());
        actions.stream().forEach(action -> coordinator.addAction(action));
        return coordinator.run();
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