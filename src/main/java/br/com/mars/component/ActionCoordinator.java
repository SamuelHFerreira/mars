package br.com.mars.component;

import java.util.ArrayList;
import java.util.List;

public class ActionCoordinator {

    private final List<Action> actions;

    public ActionCoordinator() {
        actions = new ArrayList<>();
    }

    public void addActios(Action action) {
        this.actions.add(action);
    }

    public void addActios(List<Action> actions) {
        this.actions.addAll(actions);
    }

    public void run() {
        actions.forEach(Action::perform);
    }
}