package br.com.mars.component.action.impl;

import br.com.mars.component.action.Action;
import br.com.mars.component.action.CardinalRotation;
import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

public class TurnAroundAction extends Action {

    public TurnAroundAction(Command command) {
        super(command);
    }

    @Override
    public Position perform(Position currentPosition) {
        Position.Builder builder = Position.builder(currentPosition);
        CardinalPoint newCardinalPoint = CardinalRotation.getRotation(currentPosition.getCardinalPoint(), command);
        return builder.cardinalPoint(newCardinalPoint).build();
    }
}