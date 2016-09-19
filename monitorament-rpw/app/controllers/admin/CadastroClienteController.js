(function() {
	'use strict';

	angular
		.module('cadastroClienteController.controller', [])
		.controller('CadastroClienteController' , CadastroClienteController);

	CadastroClienteController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastroClienteController($rootScope, $scope, ClienteService, UtilsService) {

		$scope.sair = UtilsService.logout;
		
		var vm = this;
		vm.inicio = inicio;
		vm.voltar = voltar;
		vm.avancarStep2 = avancarStep2;
		vm.cadastrarCliente = cadastrarCliente;

		function inicializarCadastro() {
			vm.step = 1;
			vm.cliente = {};
			vm.cliente.emailsRelatorioDiario = [];
			vm.cliente.emailsRelatorioMensal = [];
		};

		function cadastrarCliente() {
			if(validarCliente(vm.usuario)) {
				var funcSucesso = function(data) {
					alert("Cliente cadastrado com sucesso");
					UtilsService.irPara("home-administrador");

					var funcSucessoClientes = function(data) {
						$rootScope.dominios.clienteConsulta = data;
					};

					ClienteService.consultarClientes(funcSucessoClientes);
				};

				ClienteService.cadastrarCliente(vm.cliente, funcSucesso);
			}
		};

		function avancarStep2() {
			vm.step = 2;
		}
		
		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};

		function validarCliente(clienteDto) {
			return true;
		};
		
		inicializarCadastro();

	}

})();