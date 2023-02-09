package info.mastera.border.service;

import info.mastera.border.declarant.client.DeclarantApi;
import info.mastera.border.mapper.CheckpointMapper;
import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

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
    void retrieveStateData() {
        System.out.println("getting state data at " + LocalDateTime.now());
    }

    @Scheduled(every = "{waiting-area.scheduler.data-collection.checkpoints-update}")
    void retrieveCheckpointsData() {
        var actualCheckpoints = checkpointMapper.convert(declarantApi.getCheckpoints(DeclarantApi.CONSTANT_DECLARANT_TOKEN));
        checkpointsStorageService.update(actualCheckpoints);
        System.out.printf("getting checkpoints data at %s%n", LocalDateTime.now());
    }
}