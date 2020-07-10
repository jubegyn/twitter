package com.training.twitter.application;

import org.springframework.lang.NonNull;

import com.training.twitter.domain.user.User;

public interface UserService {
    User obter(@NonNull final Long id);
    User inserir(@NonNull final User user);
    User alterar(@NonNull final User user);
    void deletar(@NonNull final Long id);
    Iterable<User> listar();
}
