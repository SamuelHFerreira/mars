package br.com.mars.domain.data;

import br.com.mars.domain.constant.CardinalPoint;

public class Position {

    private Integer xAxis;
    private Integer yAxis;
    private CardinalPoint cardinalPoint;

    public Position() {
        this.xAxis = 0;
        this.yAxis = 0;
        this.cardinalPoint = CardinalPoint.NORTH;
    }

    public Position(Integer xAxis, Integer yAxis, CardinalPoint cardinalPoint) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.cardinalPoint = cardinalPoint;
    }

    public void increasexAxis() {
        this.xAxis++;
    }

    public void increaseyAxis() {
        this.yAxis++;
    }

    public void decreasexAxis() {
        this.xAxis--;
    }

    public void decreaseyAxis() {
        this.yAxis--;
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }
}