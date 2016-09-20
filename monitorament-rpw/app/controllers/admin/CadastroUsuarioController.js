(function() {
	'use strict';

	angular
		.module('cadastroUsuarioController.controller', [])
		.controller('CadastroUsuarioController' , CadastroUsuarioController);

	CadastroUsuarioController.$inject = ['$rootScope', '$scope', 'UsuarioService', 'UtilsService'];

	function CadastroUsuarioController($rootScope, $scope, UsuarioService, UtilsService) {

		$scope.sair = UtilsService.logout;

		var vm = this;

		vm.usuario = null;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.usuarioLogado = $rootScope.usuarioLogado;
		vm.cadastrarUsuario = cadastrarUsuario;

		function inicializarCadastro() {
			var usuarioCadastro = {};
			usuarioCadastro.usuario = "";
			usuarioCadastro.senha = "";
			usuarioCadastro.nome = "";
			usuarioCadastro.email = "";
			usuarioCadastro.tipoUsuario ="";
			usuarioCadastro.endereco = {};
			usuarioCadastro.endereco.logradouro ="";
			usuarioCadastro.endereco.bairro ="";
			usuarioCadastro.endereco.cidade ="";
			usuarioCadastro.endereco.estado ="";
			usuarioCadastro.endereco.cep ="";
			usuarioCadastro.idCliente ="";
			usuarioCadastro.telefone = {};
			usuarioCadastro.telefone.ddd = "";
			usuarioCadastro.telefone.telefone = "";

			vm.usuario = usuarioCadastro;

			$scope.tiposUsuarioConsulta = $rootScope.dominios.tiposUsuarioConsulta;
			$scope.clienteConsulta = $rootScope.dominios.clienteConsulta;
		};

		function cadastrarUsuario() {

			if(validarUsuario(this.usuario)) {
				var funcSucesso = function(data) {
					alert("Usuario cadastrado com sucesso");
					UtilsService.irPara("usuarios");
				};

				UsuarioService.cadastrarUsuario(this.usuario, funcSucesso);
			}
			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("usuarios");
		};

		function validarUsuario(usuarioDto) {
			return true;
		};

		inicializarCadastro();

	}

})();