package com.api.agendacontatos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.api.agendacontatos.dtos.ContatoDto;
import com.api.agendacontatos.models.ContatoModel;
import com.api.agendacontatos.services.ContatoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contato")
public class ContatoController {
	
	final ContatoService contatoService;
	
	public ContatoController(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	@PostMapping
	public ResponseEntity<Object> saveContato(@RequestBody @Valid ContatoDto contatoDto) {
		var contatoModel = new ContatoModel();
		BeanUtils.copyProperties(contatoDto, contatoModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(contatoModel));
	}
	
	@GetMapping
	public ResponseEntity<List<ContatoModel>> getAllContatos() {
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneContato(@PathVariable(value = "id") UUID id) {
		Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
		if (!contatoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contatoModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteContato(@PathVariable(value = "id") UUID id) {
		Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
		if (!contatoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
		}
		contatoService.delete(contatoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateContato(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContatoDto contatoDto) {
		Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
		if (!contatoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
		}
		var contatoModel = new ContatoModel();
		BeanUtils.copyProperties(contatoDto, contatoModel);
		contatoModel.setId(contatoModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.save(contatoModel));
	}
}