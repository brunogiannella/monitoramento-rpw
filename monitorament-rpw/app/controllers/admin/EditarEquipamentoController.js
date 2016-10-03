(function() {
	'use strict';

	angular
		.module('editarEquipamento.controller', [])
		.controller('EditarEquipamentoController' , EditarEquipamentoController);

	EditarEquipamentoController.$inject = ['$rootScope', '$scope', '$stateParams', 'EquipamentoService', 'UtilsService'];

	function EditarEquipamentoController($rootScope, $scope, $stateParams, EquipamentoService, UtilsService) {

		$scope.sair = UtilsService.logout;

		var vm = this;

		vm.equipamento = null;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.usuarioLogado = $rootScope.usuarioLogado;
		vm.cadastrarEquipamento = cadastrarEquipamento;

		function inicializarCadastro() {
			vm.equipamento = $stateParams.equipamento;
			vm.step = 1;
		};

		function cadastrarEquipamento() {

			if(validarEquipamento(this.equipamento)) {
				var funcSucesso = function(data) {
					alert("Equipamento alterado com sucesso");
					UtilsService.irPara("clientes");
				};

				EquipamentoService.atualizarEquipamento(this.equipamento, funcSucesso);
			}
			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};

		function validarEquipamento(equipamentoDto) {
			return true;
		};

		inicializarCadastro();

	}

})();