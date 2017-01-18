(function() {
	'use strict';

	angular
		.module('homeCliente.controller', [])
		.controller('HomeClienteController' , HomeClienteController);

	HomeClienteController.$inject = ['$rootScope', '$location', 'UtilsService', 'ChatService'];

	function HomeClienteController($rootScope, $location, UtilsService, ChatService) {
		var vm = this;
		vm.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		vm.mesAtual = $rootScope.dominios.descricaoMesAtual;
		vm.anoAtual = $rootScope.dominios.anoAtual;
		vm.irPara = UtilsService.irPara;
		vm.sair = UtilsService.logout;
		
		function inicializar() {
		}

		vm.meusDados = function() {
			UtilsService.irPara('meus-dados');
		};

		vm.relatorioDiario = function() {
			UtilsService.irPara('consultar-turnos-cliente', {idCliente:$rootScope.usuarioLogado.idCliente, nomeCliente:$rootScope.cliente.nome, consumidor:'cliente'});
		}

		vm.relatorioMensal = function() {
			UtilsService.irPara('relatorio-mensal', {idCliente:$rootScope.usuarioLogado.idCliente, nomeCliente:$rootScope.cliente.nome, consumidor:'cliente'});
		}

		inicializar();
	}

})();