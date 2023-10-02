package com.github.nfers.api.contract;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Subscription", description = "Subscription information")
@Path("api/v1/subscription")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SubscriptionAPI {

    @GET
    @Path("/{subscriptionId}")
    default void getById(@PathParam("subscriptionId") String subscriptionId){
        throw new ServiceUnavailableException("not implemented");
    }

}
