package info.mastera.border.declarant.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
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

    @GET
    @Path(value = "/checkpoint")
    @Produces(MediaType.APPLICATION_JSON)
    String getCheckpoints(@QueryParam("token") @DefaultValue(CONSTANT_DECLARANT_TOKEN) String token);

}
