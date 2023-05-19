package com.devnatao.logapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.logapi.domain.model.ClientEntity;
import com.devnatao.logapi.domain.repository.ClientRepository;

@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;	
	
	@GetMapping
	public List<ClientEntity> listar() {
		return clientRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientEntity> findById(@PathVariable Long id) {
		return clientRepository.findById(id)
				.map(client -> ResponseEntity.ok(client)) // or .map(ResponseEntity::ok) method reference
				.orElse(ResponseEntity.notFound().build());
		
		//Optional<ClientEntity> entity = clientRepository.findById(id);
		//return new ResponseEntity<>(entity.get(), HttpStatus.NOT_FOUND);
	}
	
	/*
	 *  Its possible to add the annotation @ResponseStatus(HttpStatus.CREATED)
	 *  and then remove ResponseEntity from method.
	 */
	@PostMapping
	public ResponseEntity<ClientEntity> create(@Valid @RequestBody ClientEntity entity){
		// hint: Returning http status CREATED (201) 
		return new ResponseEntity<>(clientRepository.save(entity), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientEntity> update(@PathVariable Long id, @RequestBody ClientEntity entity){
		if (!clientRepository.existsById(id)) return ResponseEntity.notFound().build();
		// setting the id to updateEntity - update
		ClientEntity updatedEntity = new ClientEntity();
		updatedEntity.setId(id);
		// saving the updated entity on db
		updatedEntity = clientRepository.save(entity);
		return ResponseEntity.ok(updatedEntity);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!clientRepository.existsById(id)) return ResponseEntity.noContent().build();
		clientRepository.deleteById(id);
		// hint: Returns noContent (204) when use DeleteMapping
		return ResponseEntity.noContent().build();
	}
}
