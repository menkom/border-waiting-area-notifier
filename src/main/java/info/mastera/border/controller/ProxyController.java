package info.mastera.border.controller;

import info.mastera.border.declarant.client.DeclarantApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/declarants")
public class ProxyController {

    private final DeclarantApi declarantApi;

    @Inject
    public ProxyController(@RestClient DeclarantApi declarantApi) {
        this.declarantApi = declarantApi;
    }

    @GET
    @Path("/checkpoints")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCheckpoints() {
        try {
            return Response
                    .ok(declarantApi.getCheckpoints(DeclarantApi.CONSTANT_DECLARANT_TOKEN))
                    .build();
        } catch (ResteasyWebApplicationException e) {
            return e.unwrap().getResponse();
        }
    }

    @GET
    @Path("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getState() {
        try {
            return Response
                    .ok(declarantApi.getState(DeclarantApi.CONSTANT_BERESTOVICA_ID, DeclarantApi.CONSTANT_DECLARANT_TOKEN))
                    .build();
        } catch (ResteasyWebApplicationException e) {
            return e.unwrap().getResponse();
        }
    }
}
