package br.com.mars.component.action;

import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

public abstract class Action {
    protected Command command;

    public Action(Command command) {
        this.command = command;
    }

    public abstract Position perform(final Position currentPosition);
}