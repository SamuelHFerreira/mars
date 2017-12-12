package br.com.mars.component.action;

import static br.com.mars.domain.constant.CardinalPoint.EAST;
import static br.com.mars.domain.constant.CardinalPoint.NORTH;
import static br.com.mars.domain.constant.CardinalPoint.SOUTH;
import static br.com.mars.domain.constant.CardinalPoint.WEST;

import java.util.HashMap;
import java.util.Map;

import br.com.mars.domain.constant.CardinalPoint;
import br.com.mars.domain.constant.Command;

public class CardinalRotation {

    private static final Map<CardinalPoint, Rotation> ROTATIONS_MAP = new HashMap<>();

    static {
        ROTATIONS_MAP.put(NORTH, new Rotation(WEST, EAST));
        ROTATIONS_MAP.put(EAST, new Rotation(NORTH, SOUTH));
        ROTATIONS_MAP.put(WEST, new Rotation(SOUTH, NORTH));
        ROTATIONS_MAP.put(SOUTH, new Rotation(EAST, WEST));
    }

    public static CardinalPoint getRotation(CardinalPoint originCardinalPoint, Command command) {
        return ROTATIONS_MAP.get(originCardinalPoint).getRotation(command);
    }

    protected static class Rotation {
        private Map<Command, CardinalPoint> rotations;

        public Rotation(CardinalPoint leftRotation, CardinalPoint rightRotation) {
            this.rotations = new HashMap<>();
            this.rotations.put(Command.L, leftRotation);
            this.rotations.put(Command.R, rightRotation);
        }

        public CardinalPoint getRotation(Command command) {
            return rotations.get(command);
        }
    }
}