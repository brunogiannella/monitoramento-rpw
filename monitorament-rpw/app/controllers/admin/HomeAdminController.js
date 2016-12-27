(function() {
	'use strict';

	angular
		.module('homeAdmin.controller', [])
		.controller('HomeAdminController' , HomeAdminController);

	HomeAdminController.$inject = ['$rootScope', '$location', 'UtilsService', 'ChatService'];

	function HomeAdminController($rootScope, $location, UtilsService, ChatService) {
		var vm = this;
		vm.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		vm.quantidadeClientes = $rootScope.indicadores.quantidadeClientes;
		vm.quantidadeUsuarios = $rootScope.indicadores.quantidadeUsuarios;
		vm.quantidadeTiposOcorrencia = $rootScope.indicadores.quantidadeTiposOcorrencia;
		vm.quantidadeTiposOcorrenciaPersonalizadas = $rootScope.indicadores.quantidadeTiposOcorrenciaPersonalizadas;
		vm.irPara = UtilsService.irPara;
		vm.sair = UtilsService.logout;
		vm.quantidadeMensagensNaoLidas = "";
		
		function inicializar() {
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucessoQuantidadeMensagensNaoLidas = function(data) {
				vm.quantidadeMensagensNaoLidas = data;
			};

			ChatService.consultarQuantidadeNovasMensagensUsuario(codigoUsuario, funcSucessoQuantidadeMensagensNaoLidas);
		}

		vm.clientes = function() {
			UtilsService.irPara('clientes');
		};

		vm.usuarios = function() {
			UtilsService.irPara('usuarios');
		}

		vm.tiposOcorrencia = function() {
			UtilsService.irPara('tipos-ocorrencia');
		}

		vm.mensagens = function() {
			UtilsService.irPara("chats");
		};

		vm.turnosPendentes = function() {
			UtilsService.irPara("turnos-pendentes");
		};

		inicializar();
	}

})();