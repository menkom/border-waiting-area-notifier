package info.mastera.border.service;

import info.mastera.border.model.Checkpoint;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CheckpointsStorageService {

    private List<Checkpoint> checkpoints = new ArrayList<>();

    public List<Checkpoint> get() {
        return checkpoints.stream().toList();
    }

    public void update(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
