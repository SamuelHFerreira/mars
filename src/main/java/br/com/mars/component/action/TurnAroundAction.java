package br.com.mars.component.action;

import br.com.mars.component.CardinalRotation;
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
        switch (command) {
            case L:
                builder.cardinalPoint(CardinalRotation.getLeftRotation(currentPosition.getCardinalPoint()));
            case R:
                builder.cardinalPoint(CardinalRotation.getRightRotation(currentPosition.getCardinalPoint()));
            case M:
                break;
        }
        return builder.build();
    }
}