package br.com.mars.component.location;

import br.com.mars.component.action.Action;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

public abstract class LocationManager {

    protected Position currentPosition;

    public LocationManager() {
        this.currentPosition = new Position();
    }

    public abstract Position updatePosition(Action action) throws MapViolationException;

    public abstract Position getCurrentPosition();
}