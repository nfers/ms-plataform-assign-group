package com.github.nfers.api.contract;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "EventHistory", description = "Report of EventHistory by Subscription")
@Path("api/v1/event-history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EventHistoryAPI {

    @GET
    @Path("/{subscriptionId}")
    default void getAllEventHistoryBySubscriptionId(@PathParam("subscriptionId") String subscriptionId){
            throw new ServiceUnavailableException("not implemented");
    }
}
