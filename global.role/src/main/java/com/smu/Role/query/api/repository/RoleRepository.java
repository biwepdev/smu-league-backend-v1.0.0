package com.smu.Role.query.api.repository;

import com.smu.Role.core.model.Role;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String > {
    Mono<Boolean> existsRoleByDescription(@Param("description") String description);
    Mono<Role> findByRoleCode(@Param("roleCode") String roleCode);
    @Query(value ="{}",sort = "{'role_code':-1}")
    Flux<Role> findByCodeDesc();
}
