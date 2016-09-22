(function() {
	'use strict';

	angular
		.module('cliente.controller', [])
		.controller('ClienteController' , ClienteController);

	ClienteController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function ClienteController($rootScope, $scope, ClienteService, UtilsService) {

		var vm = this;
		vm.irPara = UtilsService.irPara;
		vm.cadastrar = cadastrar;
		vm.voltar = voltar;
		vm.consultarCliente = consultarCliente;
		vm.cadastrarCamera = cadastrarCamera;
		vm.cadastrarEquipamento = cadastrarEquipamento;
		vm.associarOcorrencia = associarOcorrencia;
		vm.removerCliente = removerCliente;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			vm.edicao = false;
			vm.idCliente = null;
			vm.cliente = null;
			$scope.clienteConsulta = $rootScope.dominios.clienteConsulta;
		}

		function cadastrar() {
			UtilsService.irPara('cadastrar-cliente');
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function cadastrarCamera(id, nome) {
			UtilsService.irPara('cadastrar-camera', {idCliente:id, nomeCliente:nome});
		}

		function cadastrarEquipamento(id, nome) {
			UtilsService.irPara('cadastrar-equipamento', {idCliente:id, nomeCliente:nome});
		}

		function associarOcorrencia(id, nome) {
			UtilsService.irPara('associar-tipo-ocorrencia', {idCliente:id, nomeCliente:nome});
		}

		function removerCliente(idCliente, nomeCliente) {
			UtilsService.irPara('remover-cliente', {idCliente:idCliente, nomeCliente:nomeCliente});
		}

		function consultarCliente() {
			vm.edicao = true;

			var funcSucesso = function(data) {
				$scope.clientesListaConsulta = data;
			};

			ClienteService.consultarCliente(vm.idCliente, funcSucesso);
		}

		inicializar();

	}

})();