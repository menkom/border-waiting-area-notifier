package info.mastera.border.declarant.client.model;

import java.util.List;

public class CheckpointsResponse {

    private List<Checkpoint> result;

    public List<Checkpoint> getResult() {
        return result;
    }

    public void setResult(List<Checkpoint> result) {
        this.result = result;
    }

    public static class Checkpoint {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
