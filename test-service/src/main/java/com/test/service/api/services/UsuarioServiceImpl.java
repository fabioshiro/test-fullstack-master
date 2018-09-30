package com.test.service.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.api.usuario.Usuario;
import com.test.service.api.repositories.UsuarioRepository;

@Service("userService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository usuarioRepository;
	
	public Usuario pesquisar(Long id) {
		return usuarioRepository.findOne(id);
	}

	public void incluir(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void alterar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deletar(Long id) {
		usuarioRepository.delete(id);	
	}

	public void deletar() {
		usuarioRepository.deleteAll();
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public boolean existe(Usuario usuario) {
		return usuarioRepository.findByName(usuario.getNome()) != null;
	}
}
