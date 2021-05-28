package br.gov.rj.faeterj.estoque.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

// @Target: Onde será aplicada
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
//@Retention: Essa validação é apenas em tempo de execução
@Retention(RetentionPolicy.RUNTIME)
//@Constraint: Define uma validação por meio de uma classe (não uilizada neste caso)
@Constraint(validatedBy = {})
//@Pattern: Definição do formato padrão, será aplicado onde colocarmos @SKU
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?")
public @interface SKU {
	
	// O atributo mensagem deverá ser sobrescrito aqui, não pode ser utilizado acima
	// A anotação a ser sobreescrita será a Pattern.class e o atributo a ser reescrito
	// será o message
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "SKU deverá seguir o formato XX9999";
	
	// Declarações necessárias 
	Class<?>[] groups() default {}; // Permite agrupar validações
	Class<? extends Payload>[] payload() default{}; // Permite carregar mais informações
	                                                // por meio de uma classe para, por  
	                                                // Exemplo, classificar a extensão do erro.
	
}
