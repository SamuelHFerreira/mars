package br.com.mars.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mars.component.action.impl.TurnAroundAction;
import br.com.mars.component.action.impl.WalkAction;
import br.com.mars.component.location.LocationManager;
import br.com.mars.component.location.impl.MarsLocationManager;
import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

@RunWith(SpringRunner.class)
public class LocationManagerTest {

    @InjectMocks
    private LocationManager locationManager = new MarsLocationManager();

    @Test
    public void updateMoveCommandLocationTest() {
        final Position zeroPosition = new Position();
        final Position walkedPosition = Position.builder(zeroPosition)
                .increaseyAxis()
                .build();
        WalkAction walkAction = new WalkAction(Command.M);

        Position newPosition = locationManager.updatePosition(walkAction);
        assertThat(newPosition)
                .isEqualTo(walkedPosition);
    }

    @Test
    public void updateTurnRightCommandLocationTest() {
        final Position zeroPosition = new Position();
        final Position walkedPosition = Position.builder(zeroPosition)
                .cardinalPoint(CardinalPoint.EAST)
                .build();
        TurnAroundAction turnAroundAction = new TurnAroundAction(Command.R);

        Position newPosition = locationManager.updatePosition(turnAroundAction);
        assertThat(newPosition)
                .isEqualTo(walkedPosition);
    }

    @Test
    public void updateTurnLeftCommandLocationTest() {
        final Position zeroPosition = new Position();
        final Position walkedPosition = Position.builder(zeroPosition)
                .cardinalPoint(CardinalPoint.WEST)
                .build();
        TurnAroundAction turnAroundAction = new TurnAroundAction(Command.L);

        Position newPosition = locationManager.updatePosition(turnAroundAction);
        assertThat(newPosition)
                .isEqualTo(walkedPosition);
    }

    @Test(expected = MapViolationException.class)
    public void updateInvalidYAxisLocationTest() {
        TurnAroundAction turnAroundAction = new TurnAroundAction(Command.R);
        WalkAction walkAction = new WalkAction(Command.M);
        locationManager.updatePosition(turnAroundAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
    }

    @Test(expected = MapViolationException.class)
    public void updateInvalidXAxisLocationTest() {
        WalkAction walkAction = new WalkAction(Command.M);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
        locationManager.updatePosition(walkAction);
    }
}