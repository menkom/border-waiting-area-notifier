package info.mastera.border.service;

import info.mastera.border.model.ChangedState;
import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotifyService {

    public void notify(ChangedState changedState) {
        switch (changedState.changeType()) {
            case ORDER_ID -> Log.info(
                    "For registration number %s order changed from %s to %s".formatted(
                            changedState.regNum(),
                            changedState.previousOrderId(),
                            changedState.actualOrderId()
                    )
            );
            case STATUS -> Log.info(
                    "For registration number %s status changed from %s to %s".formatted(
                            changedState.regNum(),
                            changedState.previousStatus(),
                            changedState.actualStatus()
                    )
            );
        }
    }
}
