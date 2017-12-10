package br.com.mars.component;

import static br.com.mars.domain.constant.CardinalPoint.EAST;
import static br.com.mars.domain.constant.CardinalPoint.NORTH;
import static br.com.mars.domain.constant.CardinalPoint.SOUTH;
import static br.com.mars.domain.constant.CardinalPoint.WEST;

import java.util.HashMap;
import java.util.Map;

import br.com.mars.domain.constant.CardinalPoint;

public class CardinalRotation {

    private static final Map<CardinalPoint, Rotation> ROTATIONS_MAP = new HashMap<>();

    static {
        ROTATIONS_MAP.put(NORTH, new Rotation(WEST, EAST));
        ROTATIONS_MAP.put(EAST, new Rotation(NORTH, SOUTH));
        ROTATIONS_MAP.put(WEST, new Rotation(SOUTH, NORTH));
        ROTATIONS_MAP.put(SOUTH, new Rotation(EAST, WEST));
    }

    public static CardinalPoint getLeftRotation(CardinalPoint cardinalPoint) {
        return ROTATIONS_MAP.get(cardinalPoint).getLeftRotation();
    }

    public static CardinalPoint getRightRotation(CardinalPoint cardinalPoint) {
        return ROTATIONS_MAP.get(cardinalPoint).getRightRotation();
    }

    protected static class Rotation {
        private CardinalPoint leftRotation;
        private CardinalPoint rightRotation;

        public Rotation(CardinalPoint leftRotation, CardinalPoint rightRotation) {
            this.leftRotation = leftRotation;
            this.rightRotation = rightRotation;
        }

        public CardinalPoint getLeftRotation() {
            return leftRotation;
        }

        public CardinalPoint getRightRotation() {
            return rightRotation;
        }
    }
}