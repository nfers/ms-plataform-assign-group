package com.github.nfers.api.contract;


import com.github.nfers.service.domain.dto.MessageReceivedDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("api/v1/consumer")
@Tag(name = "Consumer", description = "Consumer message from Queue")
public interface ConsumerAPI {

    @GET
    @APIResponse(responseCode = "200", description = "Get message queue")
    @APIResponse(responseCode = "500", description = "internal server")
    default MessageReceivedDTO getMessage() {
        throw new ServiceUnavailableException("not implemented yet");
    }


}
