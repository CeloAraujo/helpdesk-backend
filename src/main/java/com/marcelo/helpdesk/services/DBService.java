package com.marcelo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.helpdesk.domain.Chamado;
import com.marcelo.helpdesk.domain.Cliente;
import com.marcelo.helpdesk.domain.Tecnico;
import com.marcelo.helpdesk.domain.enums.Perfil;
import com.marcelo.helpdesk.domain.enums.Prioridade;
import com.marcelo.helpdesk.domain.enums.Status;
import com.marcelo.helpdesk.repositories.ChamadoRepository;
import com.marcelo.helpdesk.repositories.ClienteRepository;
import com.marcelo.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Marcelo Araujo", "02524480143", "celoparaujo99@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
