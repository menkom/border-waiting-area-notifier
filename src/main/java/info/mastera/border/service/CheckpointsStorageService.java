package info.mastera.border.service;

import info.mastera.border.model.Checkpoint;
import info.mastera.border.repository.CheckpointRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CheckpointsStorageService {

    private final CheckpointRepository checkpointRepository;

    @Inject
    public CheckpointsStorageService(CheckpointRepository checkpointRepository) {
        this.checkpointRepository = checkpointRepository;
    }

    public List<Checkpoint> get() {
        return checkpointRepository.listAll();
    }

    @Transactional
    public void update(List<Checkpoint> checkpoints) {
        checkpoints.forEach(checkpointRepository::update);
    }
}
