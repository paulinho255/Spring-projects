package com.springboot.bookstore.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.springboot.bookstore.domain.Categoria;

public class CategoriaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "Name is required.")
	@Length(min = 2, max = 100, message = "The lenght must be between 2 and 100 chacters.")
	private String nome;
	@NotEmpty(message = "Description is required.")
	@Length(min = 2, max = 200, message = "The lenght must be between 2 and 200 chacters.")
	private String descricao;
	
	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
		
}
