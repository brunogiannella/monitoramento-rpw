(function() {
	'use strict';

	angular
		.module('meusDados.controller', [])
		.controller('MeusDadosController' , MeusDadosController);

	MeusDadosController.$inject = ['$rootScope', '$location', 'UtilsService', 'ChatService'];

	function MeusDadosController($rootScope, $location, UtilsService, ChatService) {
		var vm = this;
		vm.meuCliente = $rootScope.cliente;
		vm.sair = UtilsService.logout;
		vm.voltar = voltar;
		
		function voltar() {
			UtilsService.irPara('home-cliente');
		}
	}

})();