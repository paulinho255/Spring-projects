package com.springboot.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.DTO.CategoriaDTO;
import com.springboot.bookstore.domain.Categoria;
import com.springboot.bookstore.exceptions.ObjectNotFoundException;
import com.springboot.bookstore.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found for id: " + id + " Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	public Categoria update(Integer id, CategoriaDTO obj) {
		Categoria categoria = findById(id);
		categoria.setNome(obj.getNome());
		categoria.setDescricao(obj.getDescricao());
		return repository.save(categoria);
	}
}
