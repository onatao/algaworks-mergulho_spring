package com.devnatao.logapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.logapi.domain.model.ClientEntity;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	@GetMapping
	public List<ClientEntity> listar() {
		List<ClientEntity> list = new ArrayList<>();
		list.add(new ClientEntity(1L, "Nathan", "a", "b"));
		list.add(new ClientEntity(2L, "Arthur", "a", "b"));
		return list;
	}
}
