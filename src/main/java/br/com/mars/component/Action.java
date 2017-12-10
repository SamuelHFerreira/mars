package br.com.mars.component;

import br.com.mars.domain.data.Position;

@FunctionalInterface
public interface Action {
    Position perform(Position position);
}
