(function() {
	'use strict';

	angular
		.module('login.controller', [])
		.controller('LoginController' , LoginController);

	LoginController.$inject = ['$rootScope', 'LoginService', 'UtilsService'];

	function LoginController($rootScope, LoginService, UtilsService) {

		this.usuario = null;
		this.senha = null;
		this.realizarLogin = realizarLogin;

		function realizarLogin() {
			var funcSucesso = function(data) {
				$rootScope.usuarioLogado = data;

				if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRATOR") {
					UtilsService.irPara('home-administrador');
				} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
					UtilsService.irPara('home-supervisor');
				} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
					UtilsService.irPara('home-funcionario');
				} else if($rootScope.usuarioLogado.tipoUsuario == "CLIENTE") {
					UtilsService.irPara('home-cliente');
				}
				
			};

			LoginService.realizarLogin(this.usuario, this.senha, funcSucesso);
		}

	}

})();