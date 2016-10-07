(function() {
	'use strict';

	angular
		.module('homeAdmin.controller', [])
		.controller('HomeAdminController' , HomeAdminController);

	HomeAdminController.$inject = ['$rootScope', '$location', 'UtilsService'];

	function HomeAdminController($rootScope, $location, UtilsService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.quantidadeClientes = $rootScope.indicadores.quantidadeClientes;
		this.quantidadeUsuarios = $rootScope.indicadores.quantidadeUsuarios;
		this.quantidadeTiposOcorrencia = $rootScope.indicadores.quantidadeTiposOcorrencia;
		this.quantidadeTiposOcorrenciaPersonalizadas = $rootScope.indicadores.quantidadeTiposOcorrenciaPersonalizadas;
		this.irPara = UtilsService.irPara;
		this.sair = UtilsService.logout;
		
		this.clientes = function() {
			UtilsService.irPara('clientes');
		};

		this.usuarios = function() {
			UtilsService.irPara('usuarios');
		}

		this.tiposOcorrencia = function() {
			UtilsService.irPara('tipos-ocorrencia');
		}
	}

})();