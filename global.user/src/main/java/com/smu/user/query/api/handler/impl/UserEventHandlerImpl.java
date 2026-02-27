package com.smu.user.query.api.handler.impl;

import com.smu.user.cmd.api.command.*;
import com.smu.user.core.common.LogCreated;
import com.smu.user.core.common.Status;
import com.smu.user.core.model.User;
import com.smu.user.core.payload.UserPayload;
import com.smu.user.query.api.handler.UserEventHandler;
import com.smu.user.query.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {
    private final UserRepository userRepository;
    private final UserPayload userPayload;

  //  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Mono<User> create(UserCreatedCommand command) {
                        return userPayload.createUserCode()
                                .flatMap(code ->
                                {
                                    User user = User.builder()
                                            .userId(UUID.randomUUID().toString())
                                            .fullName(command.fullName())
                                            .userName(command.userName())
                                            .telephone(command.telephone())
                                            .email(command.email())
                                            .memberCode(command.memberCode())
                                            .staffCode(command.staffCode())
                                            .roleCode(command.roleCode())
                                            .roleName(command.roleName())
                                            .password(hashPassword(command.password()))
                                            .status("01")
                                            .createdAt(LogCreated.AT())
                                            .createdMonth(LogCreated.MONTH())
                                            .createdYear(LogCreated.YEAR())
                                            .createdDate(LogCreated.DATE())
                                            .userCode(code)
                                            .build();
                                    return userRepository.save(user);
                                });
                }

    @Override
    public Mono<User> enable(UserEnableCommand command) {
        return userRepository.findByUserCode (command.code ()).flatMap(user -> {
            user.setStatus(Status.enable ());
            return userRepository.save(user);
        });
    }

    @Override
    public Mono<User> disable(UserEnableCommand command) {
        return userRepository.findByUserCode (command.code ()).flatMap(user -> {
                    user.setStatus(Status.disable ());
                    return userRepository.save(user);
        });
    }

    @Override
    public Mono<User> changePassword(ChangePasswordCommand command) {
        if (!command.newPassword().equals(command.confirmPassword())) {
            return Mono.error(new IllegalArgumentException("Confirmation du mot de passe incorrecte"));
        }

        return userRepository.findById(command.userId())
                .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable")))
                .flatMap(user -> {

                    String oldHashed = hashPassword(command.oldPassword());

                    if (!oldHashed.equals(user.getPassword())) {
                        return Mono.error(new RuntimeException("Ancien mot de passe invalide"));
                    }

                    user.setPassword(hashPassword(command.newPassword()));
                    return userRepository.save(user);
                });
    }

    @Override
    public Mono<User> changeRole(ChangeRoleCommand command) {
        return userRepository.findByUserCode(command.userCode())
                .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable")))
                .flatMap(user -> {

                    user.setRoleCode(command.roleCode());
                    user.setRoleName(command.roleName());

                    return userRepository.save(user);
                });
    }

    @Override
    public Mono<User> addRole(AddRoleCommand command) {
        return userRepository.findByUserCode(command.userCode())
                .flatMap(user -> {

                    if (user.getRoleCode() != null && !user.getRoleCode().isBlank()) {
                        return Mono.error(new RuntimeException("Un rôle est déjà attribué"));
                    }

                    user.setRoleCode(command.roleCode());
                    user.setRoleName(command.roleName());

                    return userRepository.save(user);
                });
    }

    // SIMPLE SHA-256 HASH METHOD
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du hachage du mot de passe", e);
        }
    }

    @Override
    public Mono<User> addPassword(AddPasswordCommand command) {
           if (!command.newPassword().equals(command.confirmPassword())) {
      return Mono.error(new IllegalArgumentException("Les mots de passe ne correspondent pas"));
    }
    return userRepository.findById(command.userId())
            .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable")))
            .flatMap(user -> {

                if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                    return Mono.error(new RuntimeException("Mot de passe déjà défini"));
                }

                user.setPassword(hashPassword(command.newPassword()));
                return userRepository.save(user);
            });
    }
}
