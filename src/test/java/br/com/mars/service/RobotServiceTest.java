package br.com.mars.service;

import static br.com.mars.domain.constant.Command.M;
import static br.com.mars.domain.constant.Command.R;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mars.component.action.coordinator.Coordinator;
import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.exception.MapViolationException;
import br.com.mars.service.impl.RobotServiceImpl;

@RunWith(SpringRunner.class)
public class RobotServiceTest {

    @InjectMocks
    private RobotService robotService = new RobotServiceImpl();

    @Mock
    private Coordinator coordinator;

    @Test
    public void moveNorthTest() {
        List<Command> moveToNorthCommand = Arrays.asList(M);
        Position zeroPosition = new Position();
        Position walkedPosition = Position.builder(zeroPosition).increaseyAxis().build();
        when(coordinator.run()).thenReturn(walkedPosition);

        Position newPosition = robotService.followCommands(moveToNorthCommand);
        assertThat(newPosition.getyAxis())
                .isEqualTo(1);
    }

    @Test
    public void turnRightTest() {
        List<Command> turnRightCommand = Arrays.asList(R);
        Position zeroPosition = new Position();
        Position turnedPosition = Position.builder(zeroPosition)
                .cardinalPoint(CardinalPoint.EAST)
                .build();
        when(coordinator.run()).thenReturn(turnedPosition);

        Position newPosition = robotService.followCommands(turnRightCommand);
        assertThat(newPosition.getCardinalPoint())
                .isEqualTo(CardinalPoint.EAST);
    }

    @Test
    public void moveAndTurnRightTest() {
        List<Command> multiCommand = Arrays.asList(M, R);
        Position zeroPosition = new Position();
        Position changedPosition = Position.builder(zeroPosition)
                .increaseyAxis()
                .cardinalPoint(CardinalPoint.EAST)
                .build();
        when(coordinator.run()).thenReturn(changedPosition);

        Position newPosition = robotService.followCommands(multiCommand);
        assertThat(newPosition.getCardinalPoint())
                .isEqualTo(CardinalPoint.EAST);
        assertThat(newPosition.getyAxis())
                .isEqualTo(1);
    }

    @Test(expected = MapViolationException.class)
    public void moveAndTurnLeftTest() {
        List<Command> multiCommand = Arrays.asList(M, R);
        Position zeroPosition = new Position();
        Position changedPosition = Position.builder(zeroPosition)
                .increaseyAxis()
                .cardinalPoint(CardinalPoint.WEST)
                .build();
        when(coordinator.run()).thenThrow(new MapViolationException(changedPosition));

        robotService.followCommands(multiCommand);
    }

    @Test(expected = MapViolationException.class)
    public void moveOnNotValidAreaTest() {
        List<Command> tooLongCommands = Arrays.asList(M, M, M, M, M, M);
        Position zeroPosition = new Position();
        Position walkedPosition = Position.builder(zeroPosition)
                .increaseyAxis()
                .increaseyAxis()
                .increaseyAxis()
                .increaseyAxis()
                .increaseyAxis()
                .build();
        when(coordinator.run()).thenThrow(new MapViolationException(walkedPosition));
        robotService.followCommands(tooLongCommands);
    }
}