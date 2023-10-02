package com.github.nfers.api.controller;

import com.github.nfers.api.contract.PublisherAPI;
import com.github.nfers.service.PublisherService;
import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import io.netty.handler.codec.MessageAggregationException;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PublisherController implements PublisherAPI {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @Override
    public Response postMessage(SubscriptionMessageDTO dto) {
        try {
            service.publishMessage(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (MessageAggregationException e) {
            Log.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            Log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }


    }

}
