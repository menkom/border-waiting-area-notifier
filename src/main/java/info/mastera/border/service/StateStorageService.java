package info.mastera.border.service;

import info.mastera.border.model.State;
import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StateStorageService {

    private final Map<String, State> states = new ConcurrentHashMap<>();

    public Map<String, State> getStates() {
        return states;
    }

    public State getState(@NotBlank String regNum) {
        return states.get(regNum);
    }

    public void updateStates(Map<String, State> newStates) {
        states.putAll(newStates);
        Log.info("Updated states size: " + states.size());
    }
}
