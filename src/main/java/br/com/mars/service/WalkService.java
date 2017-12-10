package br.com.mars.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.component.ActionCoordinator;
import br.com.mars.component.WalkAction;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

@Service
public class WalkService {

    @Autowired ActionCoordinator actionCoordinator;

    public Position followCommands(String commands) {
        List<Command> commandList = Arrays.asList(commands.split("")).stream().map(s -> Command.valueOf(s)).collect(
                Collectors.toList());
        List<WalkAction> actions = commandList.stream().map(WalkAction::new).collect(Collectors
                .toList());
//        if (Command.M == this.command)
        actions.stream().forEach(walkAction -> actionCoordinator.addAction(walkAction));
        return actionCoordinator.run();
    }
}