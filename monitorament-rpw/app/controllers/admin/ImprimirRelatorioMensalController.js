(function() {
	'use strict';

	angular
		.module('imprimirRelatorioMensal.controller', [])
		.controller('ImprimirRelatorioMensalController' , ImprimirRelatorioMensalController);

	ImprimirRelatorioMensalController.$inject = ['$rootScope', '$scope', '$stateParams', 'UtilsService'];

	function ImprimirRelatorioMensalController($rootScope, $scope, $stateParams, UtilsService) {

		function inicializar() {
			vm.relatorio = $stateParams.relatorio;
			vm.consumidor = $stateParams.consumidor;
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