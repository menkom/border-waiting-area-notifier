package info.mastera.border.mapper;

import info.mastera.border.declarant.client.model.CheckpointsResponse;
import info.mastera.border.model.Checkpoint;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CheckpointMapper {

    public Checkpoint convert(CheckpointsResponse.Checkpoint checkpoint) {
        return new Checkpoint()
                .setId(checkpoint.getId())
                .setName(checkpoint.getName());
    }

    public List<Checkpoint> convert(CheckpointsResponse checkpoints) {
        return checkpoints.getResult().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
