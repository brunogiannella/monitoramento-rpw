(function() {
	'use strict';

	angular
		.module('cadastroClienteController.controller', [])
		.controller('CadastroClienteController' , CadastroClienteController);

	CadastroClienteController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastroClienteController($rootScope, $scope, ClienteService, UtilsService) {

		var vm = this;
		vm.inicio = inicio;
		vm.voltar = voltar;
		vm.avancarStep2 = avancarStep2;

		function inicializarCadastro() {
			vm.step = 1;
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
		
		inicializarCadastro();

	}

})();