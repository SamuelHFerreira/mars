package br.com.mars.domain.constant;

import br.com.mars.component.action.Action;
import br.com.mars.component.action.impl.TurnAroundAction;
import br.com.mars.component.action.impl.WalkAction;

public enum Command {
    L(TurnAroundAction.class),
    R(TurnAroundAction.class),
    M(WalkAction.class);

    private Class<?> action;

    Command(Class<? extends Action> action) {
        this.action = action;
    }

    public Class<?> getAction() {
        return action;
    }
}