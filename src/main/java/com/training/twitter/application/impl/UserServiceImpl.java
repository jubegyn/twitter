package com.training.twitter.application.impl;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.training.twitter.application.UserService;
import com.training.twitter.domain.user.User;
import com.training.twitter.domain.user.UserRepository;
import com.training.twitter.infra.Logger;
import com.training.twitter.infra.exception.BusinessException;
import com.training.twitter.infra.exception.NotFoundException;

@Service
public class UserServiceImpl implements Logger, UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User obter(@NonNull Long id) {
		log("obter: " + id);
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Usuário não encontrado com ID %s", id)));
	}

	@Override
	public User inserir(@NonNull User user) {
		log("obter: " + user);
		validate(user);

        Optional.ofNullable(userRepository.findByLogin(user.getLogin()))
        	.ifPresent((retorno) -> {
        		throw new BusinessException("FOUND", "Usuário já existente", HttpStatus.BAD_REQUEST.value());
        	});
		return userRepository.save(user);
	}

	@Override
	public User alterar(@NonNull User live) {
		validate(live);
		Validate.notNull(live.getId(), "Dados da Live incompleto");
		return userRepository.save(live);
	}

	@Override
	public void deletar(@NonNull Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Iterable<User> listar() {
		return userRepository.findAll();
	}

//    @Override
//    public List<User> pesquisar(String nome) {
	// return userRepository.findByNomeContains(nome);
	// }

	private static void validate(User obj) {
		Validate.notNull(obj, "Dados do user não informado");
		Validate.notNull(obj.getNome(), "Nome do user não informado");
		Validate.notNull(obj.getLogin(), "Dados do login do user não informado");
		Validate.notNull(obj.getAvatar(), "Avatar não informado");
	}
}
