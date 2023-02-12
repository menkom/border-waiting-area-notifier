package info.mastera.border.service;

import info.mastera.border.model.State;
import info.mastera.border.model.StateChangeType;
import info.mastera.border.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class ChangeInspectorServiceTest {

    private StateStorageService stateStorageService;
    private ChangeInspectorService changeInspectorService;

    @BeforeEach
    protected void init() {
        stateStorageService = Mockito.mock(StateStorageService.class);
        changeInspectorService = new ChangeInspectorService(stateStorageService);
    }

    @Test
    public void inspectNotFoundTest() {
        Mockito.doReturn(null)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());

        var resultToTest = changeInspectorService.inspect("regNum",
                new State(4, Status.ARRIVED, null, null));

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("regNum", resultToTest.regNum());
        Assertions.assertEquals(StateChangeType.NONE, resultToTest.changeType());
        Assertions.assertNull(resultToTest.previousOrderId());
        Assertions.assertEquals(4, resultToTest.actualOrderId());
        Assertions.assertNull(resultToTest.previousStatus());
        Assertions.assertEquals(Status.ARRIVED, resultToTest.actualStatus());
    }

    @Test
    public void inspectFoundAnotherJourneyTest() {
        var regDate = LocalDateTime.of(2023, 5, 30, 14, 55);
        var previousState = new State(1, null, regDate, null);
        Mockito.doReturn(previousState)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());

        var resultToTest = changeInspectorService.inspect("regNum",
                new State(4, Status.ARRIVED, regDate.plusDays(5), null));

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("regNum", resultToTest.regNum());
        Assertions.assertEquals(StateChangeType.NONE, resultToTest.changeType());
        Assertions.assertNull(resultToTest.previousOrderId());
        Assertions.assertEquals(4, resultToTest.actualOrderId());
        Assertions.assertNull(resultToTest.previousStatus());
        Assertions.assertEquals(Status.ARRIVED, resultToTest.actualStatus());
    }

    @Test
    public void inspectOrderChangedTest() {
        var regDate = LocalDateTime.of(2023, 5, 30, 14, 55);
        var previousState = new State(6, Status.ARRIVED, regDate, null);
        Mockito.doReturn(previousState)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());

        var resultToTest = changeInspectorService.inspect("regNum",
                new State(4, Status.ARRIVED, regDate, null));

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("regNum", resultToTest.regNum());
        Assertions.assertEquals(StateChangeType.ORDER_ID, resultToTest.changeType());
        Assertions.assertEquals(6, resultToTest.previousOrderId());
        Assertions.assertEquals(4, resultToTest.actualOrderId());
        Assertions.assertEquals(Status.ARRIVED, resultToTest.previousStatus());
        Assertions.assertEquals(Status.ARRIVED, resultToTest.actualStatus());
    }

    @Test
    public void inspectStatusChangedToCalledTest() {
        var regDate = LocalDateTime.of(2023, 5, 30, 14, 55);
        var previousState = new State(6, Status.ARRIVED, regDate, null);
        Mockito.doReturn(previousState)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());

        var resultToTest = changeInspectorService.inspect("regNum",
                new State(null, Status.CALLED, regDate, null));

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("regNum", resultToTest.regNum());
        Assertions.assertEquals(StateChangeType.STATUS, resultToTest.changeType());
        Assertions.assertEquals(6, resultToTest.previousOrderId());
        Assertions.assertNull(resultToTest.actualOrderId());
        Assertions.assertEquals(Status.ARRIVED, resultToTest.previousStatus());
        Assertions.assertEquals(Status.CALLED, resultToTest.actualStatus());
    }

    @Test
    public void inspectStatusChangedToAnnulledTest() {
        var regDate = LocalDateTime.of(2023, 5, 30, 14, 55);
        var previousState = new State(null, Status.CALLED, regDate, null);
        Mockito.doReturn(previousState)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());

        var resultToTest = changeInspectorService.inspect("regNum",
                new State(null, Status.ANNULLED, regDate, null));

        Assertions.assertNotNull(resultToTest);
        Assertions.assertEquals("regNum", resultToTest.regNum());
        Assertions.assertEquals(StateChangeType.STATUS, resultToTest.changeType());
        Assertions.assertNull(resultToTest.previousOrderId());
        Assertions.assertNull(resultToTest.actualOrderId());
        Assertions.assertEquals(Status.CALLED, resultToTest.previousStatus());
        Assertions.assertEquals(Status.ANNULLED, resultToTest.actualStatus());
    }
}