package br.com.mars.component;

import static br.com.mars.domain.constant.CardinalPoint.EAST;
import static br.com.mars.domain.constant.CardinalPoint.NORTH;
import static br.com.mars.domain.constant.CardinalPoint.SOUTH;
import static br.com.mars.domain.constant.CardinalPoint.WEST;

import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

public class WalkAction implements Action {

    private Command command;

    public WalkAction(Command command) {
        this.command = command;
    }

    @Override
    public Position perform(Position position) {
        if (Command.M == this.command)
            return getForwardPosition(position);
        else
            return getNewCardinalPosition(position, command);
    }

    private Position getNewCardinalPosition(Position currentPosition, Command command) {
        CardinalPoint newCardinalPoint;
        if (Command.L == command)
            newCardinalPoint = CardinalRotation.getLeftRotation(currentPosition.getCardinalPoint());
        else
            newCardinalPoint = CardinalRotation.getRightRotation(currentPosition.getCardinalPoint());
        return new Position(currentPosition.getxAxis(), currentPosition.getyAxis(), newCardinalPoint);
    }

    private Position getForwardPosition(Position currentPosition) {
        Position newPosition = new Position(currentPosition.getxAxis(),
                currentPosition.getyAxis(),
                currentPosition.getCardinalPoint());
        if (NORTH == currentPosition.getCardinalPoint())
            newPosition.increaseyAxis();
        if (SOUTH == currentPosition.getCardinalPoint())
            newPosition.decreaseyAxis();
        if (EAST == currentPosition.getCardinalPoint())
            newPosition.increasexAxis();
        if (WEST == currentPosition.getCardinalPoint())
            newPosition.decreasexAxis();
        return newPosition;
    }
}