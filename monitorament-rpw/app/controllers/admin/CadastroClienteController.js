(function() {
	'use strict';

	angular
		.module('cadastroClienteController.controller', [])
		.controller('CadastroClienteController' , CadastroClienteController);

	CadastroClienteController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastroClienteController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializarCadastro() {

		};
		
		var vm = this;
		inicializarCadastro();

	}

})();