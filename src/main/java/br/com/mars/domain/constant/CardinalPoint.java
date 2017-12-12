package br.com.mars.domain.constant;

public enum CardinalPoint {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private String shortName;

    CardinalPoint(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}