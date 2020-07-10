package com.training.twitter.application;

import org.springframework.lang.NonNull;

import com.training.twitter.domain.like.Like;

public interface LikeService {
	Like like(@NonNull final Like like);
    Iterable<Like> listar();
}
