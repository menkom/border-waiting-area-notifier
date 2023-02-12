package info.mastera.border.mapper;

import info.mastera.border.declarant.client.model.StateResponse;
import info.mastera.border.model.State;
import info.mastera.border.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class StateMapperTest {

    StateMapper stateMapper;

    @BeforeEach
    protected void init() {
        stateMapper = Mockito.spy(StateMapper.class);
    }

    @Test
    public void singleStateConvertTest() {
        var transport = create();

        var resultToTest = stateMapper.convert(transport);

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals(Status.ARRIVED, resultToTest.status());
        Assertions.assertEquals(5, resultToTest.orderId());
        Assertions.assertEquals(transport.getRegistrationDate(), resultToTest.registrationDate());
        Assertions.assertEquals(transport.getChangedDate(), resultToTest.changedDate());
    }

    @Test
    public void responseOfStatesConvertTest() {
        var response = new StateResponse();
        response.setTruckLiveQueue(List.of(create()));
        response.setTruckPriority(List.of(create()));
        response.setBusLiveQueue(List.of(create()));
        response.setBusPriority(List.of(create()));
        response.setCarLiveQueue(List.of(create()));
        response.setMotorcycleLiveQueue(List.of(create()));
        Mockito.doReturn(new State(3, null, null, null))
                .when(stateMapper)
                .convert(ArgumentMatchers.any(StateResponse.Transport.class));

        var resultToTest = stateMapper.convert(response);

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals(6, resultToTest.size());
        Assertions.assertTrue(resultToTest.values().stream().allMatch(Objects::nonNull));
    }

    private StateResponse.Transport create() {
        var transport = new StateResponse.Transport();
        transport.setRegNum(UUID.randomUUID().toString());
        transport.setStatus(2);
        transport.setOrderId(5);
        var regDate = LocalDateTime.of(2023, 1, 20, 21, 59);
        transport.setRegistrationDate(regDate);
        var changedDate = LocalDateTime.of(2023, 1, 21, 19, 58);
        transport.setChangedDate(changedDate);
        return transport;
    }
}