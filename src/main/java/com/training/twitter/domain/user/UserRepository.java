package com.training.twitter.domain.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
//    List<User> findByNomeContains(String nome);
}
