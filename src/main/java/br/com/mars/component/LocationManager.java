package br.com.mars.component;

import org.springframework.stereotype.Component;

import br.com.mars.component.action.Action;
import br.com.mars.domain.data.Position;

@Component
public class LocationManager {

    private static final Integer MAX_RANGE = 5;
    private static final Integer MIN_RANGE = 0;

    private Position currentPosition;

    public LocationManager() {
        this.currentPosition = new Position();
    }

    public void updatePosition(Action action) {
        updatePosition(action.perform(getCurrentPosition()));
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    private void updatePosition(Position newPosition) {
        if (isNewPositionValid(newPosition))
            this.currentPosition = newPosition;
    }

    private boolean isNewPositionValid(Position newPosition) {
        return isValidRange(newPosition.getxAxis()) && isValidRange(newPosition.getyAxis());
    }

    private Boolean isValidRange(Integer axis) {
        return axis <= MAX_RANGE && axis >= MIN_RANGE;
    }
}