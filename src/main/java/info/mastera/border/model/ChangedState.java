package info.mastera.border.model;

public record ChangedState(
        String regNum,
        StateChangeType changeType,
        Integer previousOrderId,
        Integer actualOrderId,
        Status previousStatus,
        Status actualStatus
) {
}
