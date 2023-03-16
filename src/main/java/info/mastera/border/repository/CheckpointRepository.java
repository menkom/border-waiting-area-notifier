package info.mastera.border.repository;

import info.mastera.border.model.Checkpoint;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckpointRepository implements PanacheRepositoryBase<Checkpoint, String> {

    public void update(Checkpoint checkpoint) {
        var existingEntity = findById(checkpoint.getName());
        if (existingEntity == null) {
            persist(checkpoint);
        } else if (!existingEntity.equals(checkpoint)) {
            checkpoint.setId(checkpoint.getId());
        }
    }
}
