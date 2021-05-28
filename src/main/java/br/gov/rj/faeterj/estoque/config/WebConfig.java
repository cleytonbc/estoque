package br.gov.rj.faeterj.estoque.config;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import br.gov.rj.faeterj.estoque.controller.ProdutosController;
import br.gov.rj.faeterj.estoque.controller.converter.EstiloConverter;
import nz.net.ultraq.thymeleaf.LayoutDialect;


@Configuration
@ComponentScan(basePackageClasses = { ProdutosController.class })//(2)Define onde encontrar os controllers
@EnableWebMvc // (1) Habilita aplicação como do tipo MVC 
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver viewResolver() {
		// (7) Quem concluirá o ciclo para encontrar as páginas HTML
		// para serem processadas
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean
	// (6) O Bean fará com que o engine fique disponível para o contexto do Spring
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		// (5) Responsável por pegar o templateResolver para colocá-lo
		// em ação, quem implementará a aplicação MVC
		// Ele irá processar os arquivos HTML
		engine.addDialect(new LayoutDialect());
		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		// (1) Quem resolve templates do Spring
		resolver.setApplicationContext(applicationContext);
		// (2) O applicationContext é um objeto do Spring que poderá ser obtido assim que
		// a aplicação subir. Para recebê-lo será necessário implementar a Interface
		// ApplicationContextAware. Com isto, será possível implementar o método
		// setApplicationContext para receber o atributo applicationContext 
		resolver.setPrefix("classpath:/templates/");
		// (3) Define onde ficam os templates, tal como o esquema mostrado 
		// na transparência 6
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		// (4) Definição do modo do template que será usado
		return resolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Permite adicionar recursos estáticos à aplicação
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new EstiloConverter());
		
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00"); // Define o padrão de formatação numérico
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter); // Para os números BigDecimal, define o formato bigDecimalFormatter 
		
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
		
		return conversionService;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
		
}