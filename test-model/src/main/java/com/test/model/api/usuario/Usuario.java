package com.test.model.api.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="TEST_USUARIO")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Campos da tabela
	//Id 
	//Nome (Obrigatório) 
	//Email (Obrigatório e válido) 
	//Telefone (Obrigatório) 
	//Sexo (Obrigatório)
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@NotEmpty
	@Email
	@Column(name="EMAIL", nullable=false)
	private String email;

	@NotEmpty
	@Column(name="TELEFONE", nullable=false)
	private String telefone;
	
	@NotEmpty
	@Column(name="SEXO", nullable=false)
	private String sexo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
