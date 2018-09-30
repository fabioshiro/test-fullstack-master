package com.test.service.api.services;

import java.util.List;

import com.test.model.api.usuario.Usuario;

public interface UsuarioService {

	Usuario pesquisar(Long id);

	void incluir(Usuario usuario);

	void alterar(Usuario usuario);

	void deletar(Long id);

	void deletar();

	List<Usuario> listar();

	boolean existe(Usuario usuario);
}
