package info.mastera.border.model;

import java.time.LocalDateTime;

public record State(
        Integer orderId,
        Status status,
        LocalDateTime registrationDate,
        LocalDateTime changedDate
) {
}
