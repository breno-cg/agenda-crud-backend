package com.api.agendacontatos.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.agendacontatos.models.ContatoModel;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, UUID> {
	
}
