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
        return new State(transport.orderId, Status.parse(transport.status),
                transport.registrationDate, transport.changedDate);
    }

    public Map<String, State> convert(StateResponse response) {
        return Stream.of(
                        response.truckLiveQueue.stream(),
                        response.truckPriority.stream(),
                        response.busLiveQueue.stream(),
                        response.busPriority.stream(),
                        response.carLiveQueue.stream(),
                        response.motorcycleLiveQueue.stream()
                )
                .flatMap(i -> i)
                .collect(Collectors.toMap(transport -> transport.regNum, this::convert));
    }
}
