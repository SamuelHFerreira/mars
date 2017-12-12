package br.com.mars.component.action.impl;

import br.com.mars.component.action.Action;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

public class WalkAction extends Action {

    public WalkAction(Command command) {
        super(command);
    }

    @Override
    public Position perform(final Position currentPosition) {
        Position.Builder builder = Position.builder(currentPosition);
        switch (currentPosition.getCardinalPoint()) {
            case NORTH:
                return builder.increaseyAxis().build();
            case SOUTH:
                return builder.decreaseyAxis().build();
            case EAST:
                return builder.increasexAxis().build();
            case WEST:
                return builder.decreasexAxis().build();
            default:
                return builder.build();
        }
    }
}