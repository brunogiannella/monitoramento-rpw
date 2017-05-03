(function() {
	'use strict';

	angular
		.module('consultarRelatorioMensal.controller', [])
		.controller('ConsultarRelatorioMensalController' , ConsultarRelatorioMensalController);

	ConsultarRelatorioMensalController.$inject = ['$rootScope', '$scope', '$stateParams', 'RelatorioService', 'UtilsService'];

	function ConsultarRelatorioMensalController($rootScope, $scope, $stateParams, RelatorioService, UtilsService) {

		function inicializar() {
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
			vm.consumidor = $stateParams.consumidor;
			vm.anoSelecionado = "";
			vm.mesSelecionado = "";
		};

		function inicio() {
			if(vm.consumidor == "cliente") {
				UtilsService.irPara('home-cliente');
			} else {
				UtilsService.irPara('home-administrador');
			}	
		}

		function voltar() {
			if(vm.consumidor == "cliente") {
				UtilsService.irPara('home-cliente');
			} else {
				UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
			}

		};

		function visualizarRelatorio() {
			UtilsService.ativarLoading();
			var funcSucesso = function(data) {
				if(data != null) {
					UtilsService.desativarLoading();
					UtilsService.irPara('imprimir-relatorio-mensal', {relatorio: data, consumidor: 'admin'});
				} else {
					alert("Ocorreu um problema ao gerar o relatório mensal.");
				}
			};

			RelatorioService.consultarRelatorioMensal(vm.mesSelecionado, vm.anoSelecionado, vm.idCliente, funcSucesso);
		}

		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.visualizarRelatorio = visualizarRelatorio;
		
		inicializar();

	}

})();