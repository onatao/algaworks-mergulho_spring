package com.devnatao.logapi.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.logapi.domain.model.ClientEntity;

@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping
	public List<ClientEntity> listar() {
		return manager.createQuery("from ClientEntity", ClientEntity.class).getResultList();
	}
}
