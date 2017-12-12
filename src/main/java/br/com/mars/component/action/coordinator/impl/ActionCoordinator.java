package br.com.mars.component.action.coordinator.impl;

import org.springframework.stereotype.Component;

import br.com.mars.component.action.coordinator.Coordinator;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

@Component
public class ActionCoordinator extends Coordinator {

    @Override
    public Position run() throws MapViolationException {
        try {
            actions.forEach(locationManager::updatePosition);
        } finally {
            actions.clear();
        }
        return locationManager.getFinalPosition();
    }
}