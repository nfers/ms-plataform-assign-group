package com.github.nfers.api.contract;

import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Publisher", description = "Publish message")
@Path("api/v1/publisher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PublisherAPI {

    @POST
    @Operation(summary = "Publish message in Queue")
    @APIResponse(responseCode = "201", description = "Created message")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "500", description = "Internal server error")
    Response postMessage(@RequestBody @Valid SubscriptionMessageDTO dto);

}
