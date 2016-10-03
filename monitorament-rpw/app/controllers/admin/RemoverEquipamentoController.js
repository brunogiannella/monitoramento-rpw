(function() {
	'use strict';

	angular
		.module('removerEquipamento.controller', [])
		.controller('RemoverEquipamentoController' , RemoverEquipamentoController);

	RemoverEquipamentoController.$inject = ['$rootScope', '$scope', '$stateParams', 'EquipamentoService', 'UtilsService'];

	function RemoverEquipamentoController($rootScope, $scope, $stateParams, EquipamentoService, UtilsService) {

		

		function inicializar() {
			vm.nomeEquipamento = $stateParams.nomeEquipamento;
			vm.idEquipamento = $stateParams.idEquipamento;
		};

		function removerEquipamento() {
			var funcSucesso = function(data) {
				alert("Equipamento removido com sucesso");
				UtilsService.irPara("clientes");
			};

			EquipamentoService.removerEquipamento(vm.idEquipamento, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};
		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerEquipamento = removerEquipamento;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();