(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', '$location'];

	function HomeController($rootScope, $location) {
		this.nomeUsuario = $rootScope.usuariLogado.nomeUsuario;

	
	}

})();