package com.test.api.status;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.api.util.CustomErrorType;
import com.test.model.api.usuario.Usuario;
import com.test.service.api.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UsuarioService usuarioService; //Service which will do all data retrieval/manipulation work

	@RequestMapping(value = "/usuario/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> usuarios = usuarioService.listar();
		if (usuarios.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisar(@PathVariable("id") long id) {
		Usuario usuario = usuarioService.pesquisar(id);
		if (usuario == null) {
			return new ResponseEntity(new CustomErrorType("Usuário com id " + id 
					+ " não encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<?> incluir(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {

		if (usuarioService.existe(usuario)) {
			return new ResponseEntity(new CustomErrorType("´Não foi possível concluir a inclusão. Um usuário com o nome " + 
			usuario.getNome() + " já existe."),HttpStatus.CONFLICT);
		}
		usuarioService.incluir(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/usuario/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		
		Usuario usuarioAtualizacao = usuarioService.pesquisar(id);

		if (usuario == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível concluir a alteração. Usuário com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}

		usuarioAtualizacao.setNome(usuario.getNome());
		usuarioAtualizacao.setEmail(usuario.getEmail());
		usuarioAtualizacao.setSexo(usuario.getSexo());
		usuarioAtualizacao.setTelefone(usuario.getTelefone());

		usuarioService.alterar(usuarioAtualizacao);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") long id) {
		Usuario usuario = usuarioService.pesquisar(id);
		if (usuario == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível concluir a exclusão. Usuário com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		usuarioService.deletar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deletar() {
		usuarioService.deletar();
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
}
