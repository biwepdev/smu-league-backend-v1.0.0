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
    Mono<User> changePassword(UserChangePasswordCommand command);
    Mono<User> addPassword(UserAddPasswordCommand command);
    Mono<User> changeRole(UserChangeRoleCommand command);
    Mono<User> addRole(UserAddRoleCommand command);
    Mono<User> changeUsername(UserChangeUsernameCommand command);
    Mono<User> cancelRole(UserCancelRoleCommand command);
}
