$(function() { /* Função que será executada após o carregamento da página */
	
	var modal = $('#modalCadastroRapidoEstilo'); /* Obtemos o id do modalCadastroRapidoEstilo */
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	var form = modal.find('form'); // Pesquisa no modal o elemento form para definir a variável */
    /* Para inibir a submissão do form será chamada a função preventDefault() */
	form.on('submit', function(event) { event.preventDefault() });
    /* Obtemos o atributo action do formulário */
	var url = form.attr('action');
    /* Obtemos o input referente ao nome do estilo */
	var inputNomeEstilo = $('#nomeEstilo');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
    /* O evento shown.bs.modal ocorre após o modal ser totalmente carregado  */	
    /* E quando isto acontecer chamaremos a função onModalShow  */	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose)
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		inputNomeEstilo.val(''); // Limpamos o campo nome do estilo
		containerMensagemErro.addClass('hidden'); // Limpa as mensagens de erro
		form.find('.form-group').removeClass('has-error'); // Limpa a classe de erro
	}
	
    /* Função ajax da biblioteca jQuery que iremos submeter para gravarmos o estilo */
 	function onBotaoSalvarClick() {
		var nomeEstilo = inputNomeEstilo.val().trim(); 
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo }),
			error: onErroSalvandoEstilo,
			success: onEstiloSalvo
		});
	}
	
	function onErroSalvandoEstilo(obj) {
	    console.log(arguments);
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo'); /* Obtém o id do estilo no CadastroProduto.html */
		/* Insere estilo no combo */
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo); /* Seleciona o estilo no combo */
		modal.modal('hide'); /* Escondemos o modal */
	}
		
});
