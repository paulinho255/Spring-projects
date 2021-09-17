package com.springboot.bookstore.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.bookstore.DTO.CategoriaDTO;
import com.springboot.bookstore.domain.Categoria;
import com.springboot.bookstore.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;
	
	/* Consulta no curl
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/categorias/1
	 * */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/* Consulta todos os dados no curl
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/categorias
	 * */
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> finAll(){
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}
	/* Envio de nova requisição
	 * curl -d "@Fornecedor.json" -H "Content-Type: application/json" -X POST http://localhost:8080/api/fornecedor
	 * 
	 * */
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria obj){
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	/* Atualizando uma informação 
	 * curl -d "@Categoria.json" -H "Content-Type: application/json" -X PUT http://localhost:8080/1
	 * */
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody CategoriaDTO obj){
		Categoria categoria = categoriaService.update(id, obj);
		return ResponseEntity.ok().body(new CategoriaDTO(categoria)); 
	}
	/* Deletar uma informação do banco de dados
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X DELETE http://localhost:8080/categorias/idCategoria
	 * */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
