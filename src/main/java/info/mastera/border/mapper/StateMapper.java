package info.mastera.border.mapper;

import info.mastera.border.declarant.client.model.StateResponse;
import info.mastera.border.model.State;
import info.mastera.border.model.Status;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class StateMapper {

    public State convert(StateResponse.Transport transport) {
        return new State(transport.getOrderId(), Status.parse(transport.getStatus()),
                transport.getRegistrationDate(), transport.getChangedDate());
    }

    public Map<String, State> convert(StateResponse response) {
        return Stream.of(
                        response.getTruckLiveQueue().stream(),
                        response.getTruckPriority().stream(),
                        response.getBusLiveQueue().stream(),
                        response.getBusPriority().stream(),
                        response.getCarLiveQueue().stream(),
                        response.getMotorcycleLiveQueue().stream()
                )
                .flatMap(i -> i)
                .collect(Collectors.toMap(StateResponse.Transport::getRegNum, this::convert));
    }
}
