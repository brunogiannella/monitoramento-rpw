(function() {
	'use strict';

	angular
		.module('imprimirRelatorio.controller', [])
		.controller('ImprimirRelatorioController' , ImprimirRelatorioController);

	ImprimirRelatorioController.$inject = ['$rootScope', '$scope', '$stateParams', 'RelatorioService', 'UtilsService'];

	function ImprimirRelatorioController($rootScope, $scope, $stateParams, RelatorioService, UtilsService) {

		

		function inicializar() {
			vm.idTurno = $stateParams.idTurno;
			vm.consumidor = $stateParams.consumidor;

			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnoConsulta = data;
				}

				UtilsService.desativarLoading();
			};

			RelatorioService.consultarRelatorioDiario(vm.idTurno, funcSucesso);
		};

		function voltar() {
			if(vm.consumidor == "admin") {
				UtilsService.irPara('home-administrador');
			} else {
				UtilsService.irPara('home-cliente');
			}
			
		}

		
		var vm = this;
		vm.voltar = voltar;
		inicializar();

	}

})();