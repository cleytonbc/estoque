package br.gov.rj.faeterj.estoque.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.gov.rj.faeterj.estoque.service.CadastroProdutoService;

@Configuration // Trata-se de uma classe de configuração
@ComponentScan(basePackageClasses = CadastroProdutoService.class)//Onde devem ser procurados 
public class ServiceConfig {                                     //os componentes dessa classe 

}
