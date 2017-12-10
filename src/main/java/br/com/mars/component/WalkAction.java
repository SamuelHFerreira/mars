package br.com.mars.component;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;

public class WalkAction implements Action {

    @Autowired
    LocationManager locationManager;

    Command command;

    public WalkAction(Command command) {
        this.command = command;
    }

    @Override
    public void perform() {
        Position position = locationManager.getCurrentPosition();
        if (Command.M.equals(this.command))
            updateForwardPosition(position);
        else
            updateNewCardinalPoint(position, command);
        locationManager.updatePosition(position);
    }

    private void updateNewCardinalPoint(Position position, Command command) {
        if (Command.L == command)
            position.setCardinalPoint(position.getCardinalPoint().getLeftRotation());
        if (Command.R == command)
            position.setCardinalPoint(position.getCardinalPoint().getRightRotation());
    }

    private void updateForwardPosition(Position position) {
        if (CardinalPoint.NORTH == position.getCardinalPoint())
            position.increasexAxis();
        if (CardinalPoint.SOUTH == position.getCardinalPoint())
            position.increasexAxis();
        if (CardinalPoint.EAST == position.getCardinalPoint())
            position.decreasexAxis();
        if (CardinalPoint.WEST == position.getCardinalPoint())
            position.decreaseyAxis();
    }
}