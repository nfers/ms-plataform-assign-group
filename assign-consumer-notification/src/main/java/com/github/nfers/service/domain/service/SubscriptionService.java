package com.github.nfers.service.domain.service;

import com.github.nfers.service.domain.entity.StatusEntity;
import com.github.nfers.service.domain.entity.SubscriptionEntity;
import com.github.nfers.service.domain.entity.enums.NotificationTypeEnum;
import com.github.nfers.service.domain.entity.enums.StatusEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class SubscriptionService {

    public Boolean checkExists(final String id) {
        return SubscriptionEntity.count("id = ?1", id) > 0;
    }

    @Transactional
    public void save(final String statusId, final String id ) {
        Long status = null;
        Optional<SubscriptionEntity> subscriptionExists = SubscriptionEntity.find("id = ?1", id)
                .firstResultOptional();

        if(statusId.equals(NotificationTypeEnum.SUBSCRIPTION_CANCELED.name())) {
             status = getIdByStatus(StatusEnum.INACTIVE);
        } else {
             status = getIdByStatus(StatusEnum.ACTIVE);
        }

        SubscriptionEntity subscriptionEntity = subscriptionExists.orElseGet(SubscriptionEntity::new);
        subscriptionEntity.setId(id);
        subscriptionEntity.setStatusId(status);
        subscriptionEntity.setStatus(StatusEntity.findById(status));
        subscriptionEntity.persist();
    }

    public Long getIdByStatus(final StatusEnum status) {
        final StatusEntity result = StatusEntity.find("name", status).firstResult();
        return result.getId();
    }

}
