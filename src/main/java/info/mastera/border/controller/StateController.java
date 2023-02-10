package info.mastera.border.controller;

import info.mastera.border.service.SchedulerService;
import info.mastera.border.service.StateStorageService;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/states")
@Produces(MediaType.APPLICATION_JSON)
public class StateController {

    private final StateStorageService stateStorageService;
    private final SchedulerService schedulerService;

    @Inject
    public StateController(
            StateStorageService stateStorageService,
            SchedulerService schedulerService
    ) {
        this.stateStorageService = stateStorageService;
        this.schedulerService = schedulerService;
    }

    @GET
    public Response getStates() {
        return Response
                .ok(stateStorageService.getStates())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestStatesUpdate() {
        schedulerService.retrieveStateData();
    }

    @GET
    @Path("/{regNum}")
    public Response getState(@NotBlank @PathParam("regNum") String regNum) {
        var state = stateStorageService.getState(regNum);
        return state == null
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(state).build();
    }
}
