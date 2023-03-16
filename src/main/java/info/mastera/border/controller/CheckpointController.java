package info.mastera.border.controller;

import info.mastera.border.service.CheckpointsStorageService;
import info.mastera.border.service.SchedulerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/checkpoints")
@Produces(MediaType.APPLICATION_JSON)
public class CheckpointController {

    private final CheckpointsStorageService checkpointsStorageService;
    private final SchedulerService schedulerService;

    @Inject
    public CheckpointController(
            CheckpointsStorageService checkpointsStorageService,
            SchedulerService schedulerService
    ) {
        this.checkpointsStorageService = checkpointsStorageService;
        this.schedulerService = schedulerService;
    }

    @GET
    public Response getCheckpoints() {
        return Response
                .ok(checkpointsStorageService.get())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestCheckpointsUpdate() {
        schedulerService.retrieveCheckpointsData();
    }
}
