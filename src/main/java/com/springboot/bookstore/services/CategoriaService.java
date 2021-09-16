package com.springboot.bookstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
