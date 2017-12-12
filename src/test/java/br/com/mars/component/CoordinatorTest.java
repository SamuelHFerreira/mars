package br.com.mars.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mars.component.action.coordinator.Coordinator;
import br.com.mars.component.action.coordinator.impl.ActionCoordinator;
import br.com.mars.component.action.impl.TurnAroundAction;
import br.com.mars.component.action.impl.WalkAction;
import br.com.mars.component.location.LocationManager;
import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;

@RunWith(SpringRunner.class)
public class CoordinatorTest {

    @InjectMocks
    private Coordinator coordinator = new ActionCoordinator();

    @Mock
    private LocationManager locationManager;

    @Test
    public void singleMoveActionTest() {
        WalkAction walkAction = new WalkAction(Command.M);
        final Position zeroPosition = new Position();
        final Position walkedPosition1 = Position.builder(zeroPosition)
                .increaseyAxis()
                .build();
        coordinator.addAction(walkAction);
        when(locationManager.updatePosition(walkAction))
                .thenReturn(walkedPosition1);
        when(locationManager.getFinalPosition()).thenReturn(walkedPosition1);

        Position finalPosition = coordinator.run();
        assertThat(finalPosition.getyAxis())
                .isEqualTo(1);
    }

    @Test
    public void twoMoveActionsTest() {
        WalkAction walkAction = new WalkAction(Command.M);
        final Position zeroPosition = new Position();
        final Position walkedPosition1 = Position.builder(zeroPosition)
                .increaseyAxis()
                .build();
        final Position walkedPosition2 = Position.builder(walkedPosition1)
                .increaseyAxis()
                .build();
        coordinator.addAction(walkAction);
        coordinator.addAction(walkAction);
        when(locationManager.updatePosition(walkAction))
                .thenReturn(walkedPosition1)
                .thenReturn(walkedPosition2);
        when(locationManager.getFinalPosition()).thenReturn(walkedPosition2);

        Position finalPosition = coordinator.run();
        assertThat(finalPosition.getyAxis())
                .isEqualTo(2);
    }

    @Test
    public void turnRightAndMoveActionsTest() {
        WalkAction walkAction = new WalkAction(Command.M);
        TurnAroundAction turnAroundAction = new TurnAroundAction(Command.R);
        final Position zeroPosition = new Position();
        final Position walkedPosition1 = Position.builder(zeroPosition)
                .increaseyAxis()
                .build();
        final Position walkedPosition2 = Position.builder(walkedPosition1)
                .cardinalPoint(CardinalPoint.EAST)
                .build();
        coordinator.addAction(walkAction);
        coordinator.addAction(turnAroundAction);
        when(locationManager.updatePosition(walkAction))
                .thenReturn(walkedPosition1);
        when(locationManager.updatePosition(turnAroundAction))
                .thenReturn(walkedPosition2);
        when(locationManager.getFinalPosition()).thenReturn(walkedPosition2);

        Position finalPosition = coordinator.run();
        assertThat(finalPosition.getyAxis())
                .isEqualTo(1);
        assertThat(finalPosition.getCardinalPoint())
                .isEqualTo(CardinalPoint.EAST);
    }

    @Test(expected = MapViolationException.class)
    public void turnLeftAndMoveActionsTest() {
        TurnAroundAction turnAroundAction = new TurnAroundAction(Command.L);
        WalkAction walkAction = new WalkAction(Command.M);
        final Position zeroPosition = new Position();
        final Position walkedPosition1 = Position.builder(zeroPosition)
                .cardinalPoint(CardinalPoint.WEST)
                .build();
        final Position walkedPosition2 = Position.builder(walkedPosition1)
                .decreasexAxis()
                .build();
        coordinator.addAction(turnAroundAction);
        coordinator.addAction(walkAction);
        when(locationManager.updatePosition(turnAroundAction))
                .thenReturn(walkedPosition1);
        when(locationManager.updatePosition(walkAction))
                .thenThrow(new MapViolationException(walkedPosition2));

        coordinator.run();
    }
}