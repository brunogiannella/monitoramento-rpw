(function() {
	'use strict';

	angular
		.module('cadastrarEquipamento.controller', [])
		.controller('CadastrarEquipamentoController' , CadastrarEquipamentoController);

	CadastrarEquipamentoController.$inject = ['$rootScope', '$scope', '$stateParams', 'CameraService', 'ClienteService', 'UtilsService', 'TipoOcorrenciaService'];

	function CadastrarEquipamentoController($rootScope, $scope, $stateParams, CameraService, ClienteService, UtilsService, TipoOcorrenciaService) {

		

		function inicializar() {
			$scope.tipoCamerasConsulta = $rootScope.dominios.tipoCamerasConsulta
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
			vm.camera = {};
			vm.camera.idCliente = $stateParams.idCliente;
		};

		function cadastrarEquipamento() {

			if(validarEquipamento(vm.camera)) {
				var funcSucesso = function(data) {
					var funcSucessoClientes = function(data) {
						$rootScope.dominios.clienteConsulta = data;
						alert("Camera cadastrada com sucesso");
						UtilsService.irPara("clientes");
					};

					ClienteService.consultarClientes(funcSucessoClientes);
				};

				CameraService.cadastrarCamera(vm.camera, funcSucesso);
			}
			
		};

		function validarEquipamento(equipamentoDto) {
			return true;
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
		$scope.sair = UtilsService.logout;
		vm.cadastrarEquipamento = cadastrarEquipamento;

		inicializar();

	}

})();