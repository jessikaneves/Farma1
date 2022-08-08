package org.generation.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.farmacia.model.CategoriaModel;
import org.generation.farmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<CategoriaModel>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByMedicamentoContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<CategoriaModel> postTema(@Valid @RequestBody CategoriaModel medicamento) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(medicamento));
	} 
	
	@PutMapping
	public ResponseEntity<CategoriaModel> put (@RequestBody CategoriaModel medicamento){
		return ResponseEntity.ok(repository.save(medicamento));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}