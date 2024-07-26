package com.marcelo.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.helpdesk.domain.Chamado;
import com.marcelo.helpdesk.repositories.ChamadoRepository;
import com.marcelo.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
				return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! Id: "+id));
	}
}
