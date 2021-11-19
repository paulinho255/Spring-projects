package com.springboot.bookstore.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.bookstore.DTO.LivroDTO;
import com.springboot.bookstore.domain.Livro;
import com.springboot.bookstore.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {

	@Autowired
	private LivroService service;

	/*
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X
	 * GET http://localhost:8080/livros/3
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id) {
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X
	 * GET http://localhost:8080/livros?categoria=3
	 */
	@GetMapping()
	public ResponseEntity<List<LivroDTO>> findAll(
			@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livro> list = service.findAll(id_cat);
		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	/*
	 * curl -d "@Livro.json" -H "Content-Type: application/json" -X PUT
	 * http://localhost:8080/livros/1
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Livro> update(@Valid @PathVariable Integer id, @RequestBody Livro obj) {
		Livro livro = service.update(id, obj);
		return ResponseEntity.ok().body(livro);
	}
	
	/*
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X
	 * POST http://localhost:8080/livros?categoria=3
	 */
	@PostMapping()
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Livro obj) {
		Livro livro = service.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
						.path("livros/{id}")
							.buildAndExpand(livro.getId())
								.toUri();
		return ResponseEntity.created(uri).build();
	}
	/* Deletar uma informação do banco de dados
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X DELETE http://localhost:8080/livros/2
	 * */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
