package com.training.twitter.application;

import org.springframework.lang.NonNull;

import com.training.twitter.domain.like.Like;

public interface LikeService {
	Boolean like(@NonNull final Long idTwitter, @NonNull Long idUsuario);
    Iterable<Like> listar();
}
