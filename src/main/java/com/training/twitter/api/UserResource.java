package com.training.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.twitter.application.UserService;
import com.training.twitter.domain.user.User;

@RestController
@RequestMapping(value = { "users" })
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public Iterable<User> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public User obter(@PathVariable Long id) {
		return service.obter(id);
	}

	@PostMapping
	public User inserir(@RequestBody User user) {
		return service.inserir(user);
	}

	@PutMapping
	public User alterar(@RequestBody User param) {
		return service.alterar(param);
	}
}
