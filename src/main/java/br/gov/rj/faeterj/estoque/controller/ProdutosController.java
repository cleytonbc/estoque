package br.gov.rj.faeterj.estoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.rj.faeterj.estoque.model.Estilo;
import br.gov.rj.faeterj.estoque.model.Origem;
import br.gov.rj.faeterj.estoque.model.Produto;
import br.gov.rj.faeterj.estoque.model.Sabor;
import br.gov.rj.faeterj.estoque.repository.Estilos;

@Controller
public class ProdutosController {

	@Autowired
//	private Produtos produtos;
	private Estilos estilos;
		
	@RequestMapping("/produtos/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("sabores", Sabor.values()); 
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values()); 
//		produtos.findAll();

		return mv;
	}
	
	@RequestMapping(value = "/produtos/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}
		System.out.println(">>> sku: " + produto.getSku());
		System.out.println(">>> Nome: " + produto.getNome());
		System.out.println(">>> Descrição: " + produto.getDescricao());
		System.out.println(">>> Sabor: " + produto.getSabor());
		System.out.println(">>> Teor alcoólico: " + produto.getTeorAlcoolico());
		System.out.println(">>> Origem: " + produto.getOrigem());
		System.out.println(">>> Valor: " + produto.getValor());
		System.out.println(">>> Comissão: " + produto.getComissao());
		System.out.println(">>> Estoque: " + produto.getQuantidadeEstoque());
		if (!produto.getEstilo().equals(null))
		   System.out.println(">>> Estilo: " + produto.getEstilo().getCodigo());
		
		// Salvar no banco de dados...
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}
}