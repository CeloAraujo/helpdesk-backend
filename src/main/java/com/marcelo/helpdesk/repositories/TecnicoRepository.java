package com.marcelo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.helpdesk.domain.Pessoa;

public interface TecnicoRepository extends JpaRepository <Pessoa,Integer>{

}
