package br.com.mars.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mars.domain.data.Position;

@Component
public class ActionCoordinator {

    private final List<Action> actions;

    private final LocationManager locationManager;

    @Autowired
    public ActionCoordinator(LocationManager locationManager) {
        actions = new ArrayList<>();
        this.locationManager = locationManager;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public Position run() {
        actions.forEach(action -> locationManager.updatePosition(action.perform(locationManager.getCurrentPosition())));
        actions.clear();
        return locationManager.getCurrentPosition();
    }
}