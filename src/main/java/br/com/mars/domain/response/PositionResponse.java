package br.com.mars.domain.response;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.mars.domain.data.Position;

public class PositionResponse {
    private Integer x;
    private Integer y;
    private String cardinalPoint;

    public PositionResponse(Position position) {
        this.x = position.getxAxis();
        this.y = position.getyAxis();
        this.cardinalPoint = position.getCardinalPoint().getShortName();
    }

    @Override
    @JsonValue
    public String toString() {
        return "(" + x + "," + y + "," + cardinalPoint + ")";
    }


}
