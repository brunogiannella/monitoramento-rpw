(function() {
	'use strict';

	angular
		.module('imprimirRelatorio.controller', [])
		.controller('ImprimirRelatorioController' , ImprimirRelatorioController);

	ImprimirRelatorioController.$inject = ['$rootScope', '$scope', '$stateParams', 'RelatorioService', 'UtilsService'];

	function ImprimirRelatorioController($rootScope, $scope, $stateParams, RelatorioService, UtilsService) {

		function inicializar() {
			vm.idTurno = $stateParams.idTurno;

			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnoConsulta = data;
				}

				UtilsService.desativarLoading();
			};

			RelatorioService.consultarRelatorioDiario(vm.idTurno, funcSucesso);
		};
		
		var vm = this;
		inicializar();

	}

})();