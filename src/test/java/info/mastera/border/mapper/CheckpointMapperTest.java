package info.mastera.border.mapper;

import info.mastera.border.declarant.client.model.CheckpointsResponse;
import info.mastera.border.model.Checkpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

public class CheckpointMapperTest {

    CheckpointMapper checkpointMapper;

    @BeforeEach
    protected void init() {
        checkpointMapper = Mockito.spy(CheckpointMapper.class);
    }

    @Test
    public void singleCheckpointConvertTest() {
        var checkpoint = new CheckpointsResponse.Checkpoint();
        checkpoint.id = "checkpoint_id";
        checkpoint.name = "checkpoint_name";

        var resultToTest = checkpointMapper.convert(checkpoint);

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("checkpoint_id", resultToTest.id());
        Assertions.assertEquals("checkpoint_name", resultToTest.name());
    }

    @Test
    public void responseOfCheckpointsConvertTest() {
        var response = new CheckpointsResponse();
        response.result = List.of(new CheckpointsResponse.Checkpoint());
        Mockito.doReturn(new Checkpoint("", ""))
                .when(checkpointMapper)
                .convert(ArgumentMatchers.any(CheckpointsResponse.Checkpoint.class));

        var resultToTest = checkpointMapper.convert(response);

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals(1, resultToTest.size());
    }
}