package br.com.mars.service;

import java.util.List;

import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

public interface RobotService {

    Position followCommands(final List<Command> commands) throws MapViolationException;

    Position getCurrentPosition();

}