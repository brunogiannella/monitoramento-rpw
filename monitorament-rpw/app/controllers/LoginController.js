(function() {
	'use strict';

	angular
		.module('login.controller', [])
		.controller('LoginController' , LoginController);

	LoginController.$inject = ['$rootScope', 'LoginService', 'UtilsService', 'UsuarioService', 'TipoOcorrenciaService', 'ClienteService'];

	function LoginController($rootScope, LoginService, UtilsService, UsuarioService, TipoOcorrenciaService, ClienteService) {

		this.usuario = null;
		this.senha = null;
		this.realizarLogin = realizarLogin;

		if($rootScope.usuarioLogado != null) {
			if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
				UtilsService.irPara('home-administrador');
			} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
				UtilsService.irPara('home-supervisor');
			} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
				UtilsService.irPara('home-funcionario');
			} else if($rootScope.usuarioLogado.tipoUsuario == "CLIENTE") {
				UtilsService.irPara('home-cliente');
			}
		}

		function realizarLogin() {
			var funcSucesso = function(data) {

				if(data != null) {
					$rootScope.usuarioLogado = data;

					if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
						carregarDominiosAdmin();
						UtilsService.irPara('home-administrador');
					} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
						UtilsService.irPara('home-supervisor');
					} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
						UtilsService.irPara('home-funcionario');
					} else if($rootScope.usuarioLogado.tipoUsuario == "CLIENTE") {
						UtilsService.irPara('home-cliente');
					}
				} else {
					alert("Usuário ou senha inválidos.");
				}
				
			};

			LoginService.realizarLogin(this.usuario, this.senha, funcSucesso);
		}

		function carregarDominiosAdmin() {

			$rootScope.dominios = {};

			var funcSucessoTiposUsuario = function(data) {
				$rootScope.dominios.tiposUsuarioConsulta = data;
			};

			UsuarioService.consultarTiposUsuario(funcSucessoTiposUsuario);


			var funcSucessoTiposCampoOcorrencia = function(data) {
				$rootScope.dominios.tiposCampoOcorrenciaConsulta = data;
			};

			TipoOcorrenciaService.listarCamposOcorrencia(funcSucessoTiposCampoOcorrencia);

			var funcSucessoClientes = function(data) {
				$rootScope.dominios.clienteConsulta = data;
			};

			ClienteService.consultarClientes(funcSucessoClientes);
		}

	}

})();