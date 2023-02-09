package info.mastera.border.service;

import info.mastera.border.declarant.client.DeclarantApi;
import info.mastera.border.mapper.CheckpointMapper;
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

    @Scheduled(every = "{waiting-area.scheduler.data-collection.state}")
    public void retrieveStateData() {
        Log.info("State updated");
    }

    @Scheduled(every = "{waiting-area.scheduler.data-collection.checkpoints-update}")
    public void retrieveCheckpointsData() {
        var actualCheckpoints = checkpointMapper.convert(declarantApi.getCheckpoints(DeclarantApi.CONSTANT_DECLARANT_TOKEN));
        checkpointsStorageService.update(actualCheckpoints);
        Log.info("Checkpoints updated");
    }
}
