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

    public Position(Builder builder) {
        this.xAxis = builder.xAxis;
        this.yAxis = builder.yAxis;
        this.cardinalPoint = builder.cardinalPoint;
    }

    public static Builder builder(Position position) {
        return new Builder(position);
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

    public static class Builder {
        private Integer xAxis;
        private Integer yAxis;
        private CardinalPoint cardinalPoint;

        public Builder(Position position) {
            this.xAxis = position.getxAxis();
            this.yAxis = position.getyAxis();
            this.cardinalPoint = position.getCardinalPoint();
        }

        public Builder cardinalPoint(CardinalPoint cardinalPoint) {
            this.cardinalPoint = cardinalPoint;
            return this;
        }

        public Builder increasexAxis() {
            this.xAxis++;
            return this;
        }

        public Builder increaseyAxis() {
            this.yAxis++;
            return this;
        }

        public Builder decreasexAxis() {
            this.xAxis--;
            return this;
        }

        public Builder decreaseyAxis() {
            this.yAxis--;
            return this;
        }

        public Position build() {
            return new Position(this);
        }
    }
}