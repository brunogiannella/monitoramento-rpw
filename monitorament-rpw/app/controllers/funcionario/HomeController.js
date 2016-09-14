(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', '$location', 'UtilsService'];

	function HomeController($rootScope, $location, UtilsService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
		this.sair = sair;

		function sair() {
			$rootScope.usuarioLogado = null;
			UtilsService.irPara("login");
		}
	}

})();