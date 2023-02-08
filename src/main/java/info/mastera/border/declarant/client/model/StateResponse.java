package info.mastera.border.declarant.client.model;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDateTime;
import java.util.List;

public class StateResponse {
    public Info info;
    public List<Transport> truckLiveQueue;
    public List<Transport> truckPriority;
    public List<Transport> busLiveQueue;
    public List<Transport> busPriority;
    public List<Transport> carLiveQueue;
    public List<Transport> motorcycleLiveQueue;

    public static class Info {
        public String id;
        public String nameEn;
        public String name;
    }

    @JsonbPropertyOrder(value = {"regNum", "status", "order_id", "type_queue", "registration_date", "changed_date"})
    public static class Transport {
        @JsonbProperty(value = "regnum")
        public String regNum;
        public int status;
        @JsonbProperty(value = "order_id", nillable = true)
        public Integer orderId;
        @JsonbProperty(value = "type_queue")
        public int typeQueue;
        @JsonbProperty(value = "registration_date")
        @JsonbDateFormat(value = "HH:mm:ss' 'dd.MM.yyyy")
        public LocalDateTime registrationDate;
        @JsonbProperty(value = "changed_date")
        @JsonbDateFormat(value = "HH:mm:ss' 'dd.MM.yyyy")
        public LocalDateTime changedDate;
    }
}
