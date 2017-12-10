package br.com.mars.component;

import org.springframework.stereotype.Component;

import br.com.mars.domain.data.Position;

@Component
public class LocationManager {

    private static final Integer MAX_X_RANGE = 5;
    private static final Integer MAX_Y_RANGE = 5;

    private Position position;

    public LocationManager() {
        this.position = new Position();
    }

    public void updatePosition(Position newPosition) {
        if (newPosition.getxAxis() <= MAX_X_RANGE)
            this.position.setxAxis(newPosition.getxAxis());
        if (newPosition.getyAxis() <= MAX_Y_RANGE)
            this.position.setyAxis(newPosition.getyAxis());
        this.position.setCardinalPoint(newPosition.getCardinalPoint());
    }

    public Position getCurrentPosition() {
        return position;
    }
}