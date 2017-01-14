(function() {
	'use strict';

	angular
		.module('consultarRelatorioMensal.controller', [])
		.controller('ConsultarRelatorioMensalController' , ConsultarRelatorioMensalController);

	ConsultarRelatorioMensalController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function ConsultarRelatorioMensalController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		function inicializar() {
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
		};

		function visualizarRelatorio(id) {
			UtilsService.irPara('imprimir-relatorio', {idTurno: id});
		}

		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.visualizarRelatorio = visualizarRelatorio;
		
		inicializar();

	}

})();