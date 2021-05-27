package br.gov.rj.faeterj.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rj.faeterj.estoque.model.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Long> {

}