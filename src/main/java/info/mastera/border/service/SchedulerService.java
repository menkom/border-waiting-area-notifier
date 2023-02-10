package info.mastera.border.service;

import info.mastera.border.declarant.client.DeclarantApi;
import info.mastera.border.mapper.CheckpointMapper;
import info.mastera.border.mapper.StateMapper;
import info.mastera.border.model.Checkpoint;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SchedulerService {

    @Inject
    @RestClient
    DeclarantApi declarantApi;
    @Inject
    CheckpointsStorageService checkpointsStorageService;
    @Inject
    CheckpointMapper checkpointMapper;
    @Inject
    StateMapper stateMapper;
    @Inject
    StateStorageService stateStorageService;
    @Inject
    ChangeInspectorService changeInspectorService;
    @Inject
    NotifyService notifyService;

    @Scheduled(every = "{waiting-area.scheduler.data-collection.state}")
    public void retrieveStateData() {
        for (Checkpoint checkpoint : checkpointsStorageService.get()) {
            Log.debug(checkpoint.name());
            var actualStates = declarantApi.getState(checkpoint.id(), DeclarantApi.CONSTANT_DECLARANT_TOKEN);
            var states = stateMapper.convert(actualStates);
            states.forEach((key, value) ->
                    notifyService.notify(changeInspectorService.inspect(key, value)));
            stateStorageService.updateStates(states);
        }
        Log.info("States updated");
    }

    @Scheduled(every = "{waiting-area.scheduler.data-collection.checkpoints-update}")
    public void retrieveCheckpointsData() {
        var actualCheckpoints = checkpointMapper.convert(declarantApi.getCheckpoints(DeclarantApi.CONSTANT_DECLARANT_TOKEN));
        checkpointsStorageService.update(actualCheckpoints);
        Log.info("Checkpoints updated");
    }
}
