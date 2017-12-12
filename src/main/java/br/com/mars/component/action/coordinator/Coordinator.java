package br.com.mars.component.action.coordinator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.mars.component.action.Action;
import br.com.mars.component.location.LocationManager;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

public abstract class Coordinator {

    protected final List<Action> actions;

    @Autowired
    protected LocationManager locationManager;

    protected Coordinator() {
        actions = new ArrayList<>();
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public abstract Position run() throws MapViolationException;
}