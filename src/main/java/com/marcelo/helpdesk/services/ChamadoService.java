package com.marcelo.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.helpdesk.domain.Chamado;
import com.marcelo.helpdesk.domain.Cliente;
import com.marcelo.helpdesk.domain.Tecnico;
import com.marcelo.helpdesk.domain.dtos.ChamadoDTO;
import com.marcelo.helpdesk.domain.enums.Prioridade;
import com.marcelo.helpdesk.domain.enums.Status;
import com.marcelo.helpdesk.repositories.ChamadoRepository;
import com.marcelo.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
				return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! Id: "+id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return repository.save(newChamado(objDTO));
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findbyId(id);
		oldObj = newChamado(objDTO);
		
		return repository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
		
	}

	
}
