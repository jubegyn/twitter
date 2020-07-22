package com.training.twitter.domain.comments;

import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments, Long> {
	Iterable<Comments> findByTwitterId(Long twitterId);
	void deleteByTwitterId(Long id);
	long countByTwitterId(Long twitterId);
}
