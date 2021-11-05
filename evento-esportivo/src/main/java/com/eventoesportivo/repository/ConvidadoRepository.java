package com.eventoesportivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventoesportivo.model.Convidado;

@Repository
@Transactional
public interface ConvidadoRepository extends CrudRepository<Convidado, Long>{
	
   @Query("select c from Convidado c where c.evento.id = ?1")	
   public List<Convidado> getConvidados(Long eventoid);
}
