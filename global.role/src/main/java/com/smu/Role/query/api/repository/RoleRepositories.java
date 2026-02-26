package com.smu.Role.query.api.repository;

import com.smu.Role.core.model.Role;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepositories {
  Mono<Role> getLastElement();
}
