package br.gov.rj.faeterj.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutosController {
	// (1) Método que devolve o nome da view
	// e para o DispatcherServlet encontrar o controller
	// ele deverá estar mapeado
	@RequestMapping("/produtos/novo") // Configurando a URL
	public String novo() {
		return "produto/CadastroProduto"; // Retorno do nome da view
	}
	// A partir deste controller será possível renderizar o HTML
	
}
