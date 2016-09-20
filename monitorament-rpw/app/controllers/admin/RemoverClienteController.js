(function() {
	'use strict';

	angular
		.module('removerCliente.controller', [])
		.controller('RemoverClienteController' , RemoverClienteController);

	RemoverClienteController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function RemoverClienteController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		

		function inicializar() {
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function removerCliente() {
			var funcSucesso = function(data) {
				var funcSucessoClientes = function(data) {
					$rootScope.dominios.clienteConsulta = data;
					alert("Cliente removido com sucesso");
					UtilsService.irPara("clientes");
				};

				ClienteService.consultarClientes(funcSucessoClientes);
			};

			ClienteService.removerCliente(vm.idCliente, funcSucesso);			
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
		vm.removerCliente = removerCliente;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();