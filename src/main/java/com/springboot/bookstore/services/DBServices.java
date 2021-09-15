package com.springboot.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.domain.Categoria;
import com.springboot.bookstore.domain.Livro;
import com.springboot.bookstore.repository.CategoriaRepository;
import com.springboot.bookstore.repository.LivroRepository;

@Service
public class DBServices {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	public void instanciaDb() {
		Categoria cat1 = new Categoria(null, "informatica","Livro de TI");
		Categoria cat2 = new Categoria(null, "Economia","Investimentos");
		Categoria cat3 = new Categoria(null, "Direito","Direito Penal");
		
		Livro l1 = new Livro(null,"Clean Code","Full Cycle", "Lorem Ipsum",cat1);
		Livro l2 = new Livro(null,"Learning Angular","A Data", "Learning Angular from Scratch, with the most popular methods",cat1);
		Livro l3 = new Livro(null,"O Mercado Financeiro","AGC Economia", "Aprendendo a lidar com as flutuações do mercado brasilero e estrangeiro",cat2);
		Livro l4 = new Livro(null,"A Constituição Brasileira","Legal Information Associate", "Entendo de leis de forma clara e concisa. ",cat3);
		
		cat1.getLivros().addAll(Arrays.asList(l1,l2));
		cat2.getLivros().addAll(Arrays.asList(l3));
		cat3.getLivros().addAll(Arrays.asList(l4));
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4));
	}
}
