package info.mastera.border.declarant.client.model;

import java.util.List;

public class CheckpointsResponse {

    public List<Checkpoint> result;

    public static class Checkpoint{
        public String id;
        public String name;
        public int countAll;
        public int countCar;
        public int countTruck;
        public int countBus;
        public int countMotorcycle;
        public int countLiveQueue;
        public int countBookings;
        public int countPriority;
        public int countPassedSCC;
    }
}
