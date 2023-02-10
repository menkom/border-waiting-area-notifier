package info.mastera.border.declarant.client.model;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDateTime;
import java.util.List;

public class StateResponse {
    private List<Transport> truckLiveQueue;
    private List<Transport> truckPriority;
    private List<Transport> busLiveQueue;
    private List<Transport> busPriority;
    private List<Transport> carLiveQueue;
    private List<Transport> motorcycleLiveQueue;

    @JsonbPropertyOrder(value = {"regNum", "status", "order_id", "type_queue", "registration_date", "changed_date"})
    public static class Transport {
        @JsonbProperty(value = "regnum")
        private String regNum;
        private int status;
        @JsonbProperty(value = "order_id", nillable = true)
        private Integer orderId;
        @JsonbProperty(value = "type_queue")
        private int typeQueue;
        @JsonbProperty(value = "registration_date")
        @JsonbDateFormat(value = "HH:mm:ss' 'dd.MM.yyyy")
        private LocalDateTime registrationDate;
        @JsonbProperty(value = "changed_date")
        @JsonbDateFormat(value = "HH:mm:ss' 'dd.MM.yyyy")
        private LocalDateTime changedDate;

        public String getRegNum() {
            return regNum;
        }

        public void setRegNum(String regNum) {
            this.regNum = regNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public int getTypeQueue() {
            return typeQueue;
        }

        public void setTypeQueue(int typeQueue) {
            this.typeQueue = typeQueue;
        }

        public LocalDateTime getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
        }

        public LocalDateTime getChangedDate() {
            return changedDate;
        }

        public void setChangedDate(LocalDateTime changedDate) {
            this.changedDate = changedDate;
        }
    }

    public List<Transport> getTruckLiveQueue() {
        return truckLiveQueue;
    }

    public void setTruckLiveQueue(List<Transport> truckLiveQueue) {
        this.truckLiveQueue = truckLiveQueue;
    }

    public List<Transport> getTruckPriority() {
        return truckPriority;
    }

    public void setTruckPriority(List<Transport> truckPriority) {
        this.truckPriority = truckPriority;
    }

    public List<Transport> getBusLiveQueue() {
        return busLiveQueue;
    }

    public void setBusLiveQueue(List<Transport> busLiveQueue) {
        this.busLiveQueue = busLiveQueue;
    }

    public List<Transport> getBusPriority() {
        return busPriority;
    }

    public void setBusPriority(List<Transport> busPriority) {
        this.busPriority = busPriority;
    }

    public List<Transport> getCarLiveQueue() {
        return carLiveQueue;
    }

    public void setCarLiveQueue(List<Transport> carLiveQueue) {
        this.carLiveQueue = carLiveQueue;
    }

    public List<Transport> getMotorcycleLiveQueue() {
        return motorcycleLiveQueue;
    }

    public void setMotorcycleLiveQueue(List<Transport> motorcycleLiveQueue) {
        this.motorcycleLiveQueue = motorcycleLiveQueue;
    }
}
