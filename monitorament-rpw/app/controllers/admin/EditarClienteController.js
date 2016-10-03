(function() {
	'use strict';

	angular
		.module('editarCliente.controller', [])
		.controller('EditarClienteController' , EditarClienteController);

	EditarClienteController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function EditarClienteController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		$scope.sair = UtilsService.logout;

		var vm = this;

		vm.cliente = null;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.voltarPasso1 = voltarPasso1;
		vm.avancarStep2 = avancarStep2;
		vm.usuarioLogado = $rootScope.usuarioLogado;
		vm.cadastrarCliente = cadastrarCliente;

		function inicializarCadastro() {
			vm.cliente = $stateParams.cliente;
			vm.step = 1;
		};

		function cadastrarCliente() {

			if(validarCliente(this.cliente)) {
				var funcSucesso = function(data) {
					alert("Cliente alterado com sucesso");
					UtilsService.irPara("clientes");
				};

				ClienteService.atualizarCliente(this.cliente, funcSucesso);
			}
			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};

		function validarCliente(clienteDto) {
			return true;
		};

		function avancarStep2() {
			vm.step = 2;
		}

		function voltarPasso1() {
			vm.step = 1;
		}

		inicializarCadastro();

	}

})();