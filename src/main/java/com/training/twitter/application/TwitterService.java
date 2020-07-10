package com.training.twitter.application;

import org.springframework.lang.NonNull;

import com.training.twitter.domain.twitter.Twitter;

public interface TwitterService {
	Twitter obter(@NonNull final Long id);
	Twitter inserir(@NonNull final Twitter twitter);
	Twitter alterar(@NonNull final Twitter twitter);
	void deletar(@NonNull final Long idTwitter, @NonNull final Long idUser);
    Iterable<Twitter> listar();
}
