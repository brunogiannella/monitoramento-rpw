(function() {
	'use strict';

	angular
		.module('orientacoesEnvio.controller', [])
		.controller('OrientacoesEnvioController' , OrientacoesEnvioController);

	OrientacoesEnvioController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function OrientacoesEnvioController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		var vm = this;
		vm.sair = UtilsService.logout;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.imprimirRelatorio = imprimirRelatorio;
		vm.emailsRelatorioDiario = null;
		vm.nomeCliente = null;
		

		function inicializar() {
			vm.codigoTurno = $stateParams.idTurno;

			var funcSucesso = function(data) {
				vm.emailsRelatorioDiario = data.emailsRelatorioDiario;
				vm.nomeCliente = data.nomeCliente;
			};

			TurnoService.consultarTurno(vm.codigoTurno, funcSucesso);
		}

		function imprimirRelatorio() {
			UtilsService.irPara("imprimir-relatorio", {idTurno: vm.codigoTurno, consumidor: 'funcionario'});
		}

		function voltar() {
			UtilsService.irPara("home-funcionario");
		}

		function inicio() {
			UtilsService.irPara("home-funcionario");
		}
		
		inicializar();

	}

})();