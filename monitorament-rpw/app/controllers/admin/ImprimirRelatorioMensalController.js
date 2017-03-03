(function() {
	'use strict';

	angular
		.module('imprimirRelatorioMensal.controller', [])
		.controller('ImprimirRelatorioMensalController' , ImprimirRelatorioMensalController);

	ImprimirRelatorioMensalController.$inject = ['$rootScope', '$scope', '$stateParams', 'UtilsService'];

	function ImprimirRelatorioMensalController($rootScope, $scope, $stateParams, UtilsService) {

		function inicializar() {
			vm.relatorio = $stateParams.relatorio;
			vm.equipamentos = [];
			vm.consumidor = $stateParams.consumidor;


			if(vm.relatorio.gruposEquipamento != null) {
				for(var i = 0; i < vm.relatorio.gruposEquipamento.length; i++) {
					if(vm.relatorio.gruposEquipamento[i].horasInoperantes != null) {
						for(var y = 0; y < vm.relatorio.gruposEquipamento[i].horasInoperantes.length; y++) {
							if(vm.relatorio.gruposEquipamento[i].horasInoperantes[y] != null) {
								vm.equipamentos[vm.equipamentos.length + 1] = vm.relatorio.gruposEquipamento[i].horasInoperantes[y];
							}	
						}
					}
				}
			}
	
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