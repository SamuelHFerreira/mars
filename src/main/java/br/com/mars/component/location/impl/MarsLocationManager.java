package br.com.mars.component.location.impl;

import org.springframework.stereotype.Component;

import br.com.mars.component.action.Action;
import br.com.mars.component.location.LocationManager;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

@Component
public class MarsLocationManager extends LocationManager {
    private static final Integer MAX_RANGE = 5;
    private static final Integer MIN_RANGE = 0;

    @Override
    public Position updatePosition(Action action) throws MapViolationException {
        return updatePosition(action.perform(getCurrentPosition()));
    }

    @Override
    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    private Position updatePosition(Position newPosition) throws MapViolationException {
        if (isNewPositionValid(newPosition))
            return this.currentPosition = newPosition;
        else
            throw new MapViolationException(newPosition);
    }

    private boolean isNewPositionValid(Position newPosition) {
        return isValidRange(newPosition.getxAxis()) && isValidRange(newPosition.getyAxis());
    }

    private Boolean isValidRange(Integer axis) {
        return axis <= MAX_RANGE && axis >= MIN_RANGE;
    }
}
