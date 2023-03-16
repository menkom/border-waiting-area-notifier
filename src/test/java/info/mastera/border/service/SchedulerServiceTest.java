package info.mastera.border.service;

import info.mastera.border.declarant.client.DeclarantApi;
import info.mastera.border.declarant.client.model.CheckpointsResponse;
import info.mastera.border.mapper.CheckpointMapper;
import info.mastera.border.model.Checkpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
class SchedulerServiceTest {

    @Inject
    SchedulerService schedulerService;
    @InjectMock
    @RestClient
    DeclarantApi declarantApi;
    @InjectMock
    CheckpointsStorageService checkpointsStorageService;
    @InjectMock
    CheckpointMapper checkpointMapper;

    List<Checkpoint> checkpoints;

    @BeforeEach
    protected void init() {
        checkpoints = List.of(
                new Checkpoint().
                setId("id")
                .setName("name")
        );
        Mockito.doReturn(checkpoints)
                .when(checkpointMapper)
                .convert(ArgumentMatchers.any(CheckpointsResponse.class));
        Mockito.doReturn(Mockito.mock(CheckpointsResponse.class))
                .when(declarantApi)
                .getCheckpoints(ArgumentMatchers.any());
    }

    @Test
    void retrieveCheckpointsDataTest() {
        schedulerService.retrieveCheckpointsData();

        Mockito.verify(declarantApi)
                .getCheckpoints(ArgumentMatchers.anyString());
        Mockito.verify(checkpointMapper)
                .convert(ArgumentMatchers.any(CheckpointsResponse.class));
        Mockito.verify(checkpointsStorageService)
                .update(checkpoints);
        Mockito.inOrder(declarantApi, checkpointMapper, checkpointsStorageService);
    }
}