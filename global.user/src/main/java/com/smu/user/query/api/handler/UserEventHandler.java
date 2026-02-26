package com.smu.user.query.api.handler;

import com.smu.user.cmd.api.command.*;
//import com.smu.user.cmd.api.command.UserPasswordCommand;
//import com.smu.user.cmd.api.command.UserUpdatedCommand;
import com.smu.user.core.model.User;
import reactor.core.publisher.Mono;

public interface UserEventHandler {
    Mono<User> create(UserCreatedCommand command);
    Mono<User> enable(UserEnableCommand command);
    Mono<User> disable(UserEnableCommand command);
    Mono<User> changePassword(ChangePasswordCommand command);
//   Mono<User> addPassword(ChangePasswordCommand command);
    Mono<User> changeRole(ChangeRoleCommand command);
    Mono<User> addRole(AddRoleCommand command);
}
