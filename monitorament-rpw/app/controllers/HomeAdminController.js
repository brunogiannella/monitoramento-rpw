(function() {
	'use strict';

	angular
		.module('homeAdmin.controller', [])
		.controller('HomeAdminController' , HomeAdminController);

	HomeAdminController.$inject = ['$rootScope', '$location', 'UtilsService'];

	function HomeAdminController($rootScope, $location, UtilsService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
	}

})();