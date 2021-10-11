package com.springboot.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.domain.Categoria;
import com.springboot.bookstore.domain.Livro;
import com.springboot.bookstore.exceptions.ObjectNotFoundException;
import com.springboot.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private CategoriaService categoriaRepository;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found for id: " + id + " Tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaRepository.findById(id_cat); 
		return livroRepository.findAllByCategory(id_cat);
	}

	public Livro update(Integer id, Livro obj) {
		 Livro newObj = findById(id);
		 updatedate(newObj,obj);
		return livroRepository.save(newObj);
	}

	private void updatedate(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria categoria = categoriaRepository.findById(id_cat);
		obj.setCategoria(categoria);
		return livroRepository.save(obj);
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		livroRepository.delete(obj);
	}
}
