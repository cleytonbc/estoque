package br.gov.rj.faeterj.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.rj.faeterj.estoque.model.Estilo;
import br.gov.rj.faeterj.estoque.repository.Estilos;
import br.gov.rj.faeterj.estoque.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private Estilos estilos;

		@Transactional
		public Estilo salvar(Estilo estilo) {
			Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
			if (estiloOptional.isPresent()) {
				// Caso o nome existe, lançamos uma exceção
				throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
			}
			return estilos.saveAndFlush(estilo);
		}
		
}

