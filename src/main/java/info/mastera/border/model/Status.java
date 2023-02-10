package info.mastera.border.model;

import java.util.stream.Stream;

public enum Status {
    ARRIVED(2),
    CALLED(3),
    ANNULLED(9)
    ;

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public static Status parse(int valueCode) {
        return Stream.of(Status.class.getEnumConstants())
                .filter(item -> item.code == valueCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        StringItems.ERROR_ENUM_CODE_PARSE
                                .formatted(Status.class.getSimpleName(), valueCode)
                ));
    }
}
