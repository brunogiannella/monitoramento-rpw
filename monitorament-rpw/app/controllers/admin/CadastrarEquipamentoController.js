(function() {
	'use strict';

	angular
		.module('cadastrarEquipamento.controller', [])
		.controller('CadastrarEquipamentoController' , CadastrarEquipamentoController);

	CadastrarEquipamentoController.$inject = ['$rootScope', '$scope', '$stateParams', 'EquipamentoService', 'ClienteService', 'UtilsService', 'TipoOcorrenciaService'];

	function CadastrarEquipamentoController($rootScope, $scope, $stateParams, EquipamentoService, ClienteService, UtilsService, TipoOcorrenciaService) {

		

		function inicializar() {
			$scope.tipoEquipamentosConsulta = $rootScope.dominios.tipoEquipamentosConsulta
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
			vm.equipamento = {};
			vm.equipamento.idCliente = $stateParams.idCliente;
		};

		function cadastrarEquipamento() {

			if(validarEquipamento(vm.camera)) {
				var funcSucesso = function(data) {
					var funcSucessoClientes = function(data) {
						$rootScope.dominios.clienteConsulta = data;
						alert("Equipamento cadastrado com sucesso");
						UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
					};

					ClienteService.consultarClientes(funcSucessoClientes);
				};

				EquipamentoService.cadastrarEquipamento(vm.equipamento, funcSucesso);
			}
			
		};

		function validarEquipamento(equipamentoDto) {
			return true;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.cadastrarEquipamento = cadastrarEquipamento;

		inicializar();

	}

})();