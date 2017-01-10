(function() {
	'use strict';

	angular
		.module('imprimirRelatorio.controller', [])
		.controller('ImprimirRelatorioController' , ImprimirRelatorioController);

	ImprimirRelatorioController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function ImprimirRelatorioController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		

		function inicializar() {
			vm.idTurno = $stateParams.idTurno;

			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnoConsulta = data;
				}

				UtilsService.desativarLoading();
			};

			TurnoService.consultarDetalheTurno(vm.idTurno, funcSucesso);
		};

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		
		var vm = this;
		vm.voltar = voltar;
		inicializar();

	}

})();