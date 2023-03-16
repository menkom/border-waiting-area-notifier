package info.mastera.border.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(
        name = "checkpoint",
        uniqueConstraints = {@UniqueConstraint(name = "uk_checkpoint_id", columnNames = {"id"})}
)
public class Checkpoint {

    @Id
    @Column(length = 40)
    private String name;

    @Column(length = 40, nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public Checkpoint setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Checkpoint setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkpoint that = (Checkpoint) o;
        return getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
