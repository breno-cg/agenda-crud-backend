package com.api.agendacontatos.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.agendacontatos.models.ContatoModel;
import com.api.agendacontatos.repositories.ContatoRepository;

@Service
public class ContatoService {

	final ContatoRepository contatoRepository;
	
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}

	@Transactional
	public ContatoModel save(ContatoModel contatoModel) {
		return contatoRepository.save(contatoModel);
	}

	public List<ContatoModel> findAll() {
		return contatoRepository.findAll();
	}

	public Optional<ContatoModel> findById(UUID id) {
		return contatoRepository.findById(id);
	}

	public void delete(ContatoModel contatoModel) {
		contatoRepository.delete(contatoModel);
	}	
}