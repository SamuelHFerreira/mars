package br.com.mars.domain.constant;

public enum CardinalPoint {
    NORTH(CardinalPoint.WEST, CardinalPoint.EAST),
    EAST(CardinalPoint.NORTH, CardinalPoint.SOUTH),
    WEST(CardinalPoint.SOUTH, CardinalPoint.NORTH),
    SOUTH(CardinalPoint.WEST, CardinalPoint.EAST);

    final CardinalPoint leftRotation;
    final CardinalPoint rightRotation;

    CardinalPoint(CardinalPoint leftRotation, CardinalPoint rightRotation) {
        this.leftRotation = leftRotation;
        this.rightRotation = rightRotation;
    }

    public CardinalPoint getLeftRotation() {
        return this.leftRotation;
    }

    public CardinalPoint getRightRotation() {
        return this.rightRotation;
    }
}