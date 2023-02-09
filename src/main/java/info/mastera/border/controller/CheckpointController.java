package info.mastera.border.controller;

import info.mastera.border.service.CheckpointsStorageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/checkpoints")
public class CheckpointController {

    @Inject
    CheckpointsStorageService checkpointsStorageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCheckpoints() {
        return Response
                .ok(checkpointsStorageService.get())
                .build();
    }
}
