package info.mastera.border.declarant.client;

import info.mastera.border.declarant.client.model.CheckpointsResponse;
import info.mastera.border.declarant.client.model.StateResponse;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@ApplicationScoped
@RegisterRestClient
public interface DeclarantApi {

    String CONSTANT_DECLARANT_TOKEN = "bts47d5f-6420-4f74-8f78-42e8e4370cc4";
    String CONSTANT_BERESTOVICA_ID = "7e46a2d1-ab2f-11ec-bafb-ac1f6bf889c1";

    @GET
    @Path(value = "/checkpoint")
    @Produces(MediaType.APPLICATION_JSON)
    CheckpointsResponse getCheckpoints(@QueryParam("token") @DefaultValue(CONSTANT_DECLARANT_TOKEN) String token);

    @GET
    @Path(value = "/monitoring")
    @Produces(MediaType.APPLICATION_JSON)
    @ClientHeaderParam(name = "Origin", value = "https://mon.declarant.by")
    StateResponse getState(
            @NotBlank @QueryParam("checkpointId") String checkpointId,
            @QueryParam("token") @DefaultValue(CONSTANT_DECLARANT_TOKEN) String token
    );

}
