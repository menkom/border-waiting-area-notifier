package info.mastera.border.service;

import info.mastera.border.model.ChangedState;
import info.mastera.border.model.State;
import info.mastera.border.model.StateChangeType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class ChangeInspectorService {

    private final StateStorageService stateStorageService;

    @Inject
    public ChangeInspectorService(StateStorageService stateStorageService) {
        this.stateStorageService = stateStorageService;
    }

    public ChangedState inspect(String regNum, State actualState) {
        var previousState = stateStorageService.getState(regNum);
        // Transport was never saved in the system or has saved info from previous journey
        if (previousState == null || !previousState.registrationDate().isEqual(actualState.registrationDate())) {
            return new ChangedState(regNum, StateChangeType.NONE,
                    null, actualState.orderId(),
                    null, actualState.status());
        } else {
            StateChangeType changeType = StateChangeType.NONE;
            if (actualState.orderId() != null && !Objects.equals(previousState.orderId(), actualState.orderId())) {
                changeType = StateChangeType.ORDER_ID;
            } else if (!Objects.equals(previousState.status(), actualState.status())) {
                changeType = StateChangeType.STATUS;
            }
            return new ChangedState(regNum, changeType,
                    previousState.orderId(), actualState.orderId(),
                    previousState.status(), actualState.status());
        }
    }
}
