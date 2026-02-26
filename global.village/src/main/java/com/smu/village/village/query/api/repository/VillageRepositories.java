package com.smu.village.village.query.api.repository;

import com.smu.village.village.core.model.Village;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VillageRepositories {
  Mono<Village> getLastElement();
}
