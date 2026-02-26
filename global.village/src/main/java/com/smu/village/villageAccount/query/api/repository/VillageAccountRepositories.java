package com.smu.village.villageAccount.query.api.repository;

import com.smu.village.villageAccount.core.model.VillageAccount;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VillageAccountRepositories {
  Mono<VillageAccount> getLastElement();
}
