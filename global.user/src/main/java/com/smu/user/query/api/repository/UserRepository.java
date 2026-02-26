package com.smu.user.query.api.repository;

import com.smu.user.core.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String > {
    Mono<User> findByUserCode(@Param("userCode") String userCode);
    Flux<User> findByStaffCode(@Param("staffCode") String staffCode);
    Flux<User> findByRoleCode(@Param("roleCode") String roleCode);
    Flux<User> findByMemberCode(@Param("memberCode") String memberCode);
    @Query(value ="{}",sort = "{'userCode':-1}")
    Flux<User> findByCodeDesc();
}
