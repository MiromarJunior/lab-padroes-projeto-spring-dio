package br.com.padroesdeprojeto.labpadroesprojetospring.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.padroesdeprojeto.labpadroesprojetospring.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
    
}
