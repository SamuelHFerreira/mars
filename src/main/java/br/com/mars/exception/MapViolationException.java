package br.com.mars.exception;

import br.com.mars.domain.data.Position;

public class MapViolationException extends RuntimeException {

    private static final long serialVersionUID = -8126334505389180764L;

    private Position position;

    public MapViolationException(Position position) {
        super("Invalid position: " + position.toString());
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}