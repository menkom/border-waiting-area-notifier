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

    @Inject
    @RestClient
    DeclarantApi declarantApi;

    @GET
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
}
