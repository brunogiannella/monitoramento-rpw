(function() {
	'use strict';

	angular
		.module('cadastroUsuarioController.controller', [])
		.controller('CadastroUsuarioController' , CadastroUsuarioController);

	CadastroUsuarioController.$inject = ['$rootScope', '$scope', 'UsuarioService', 'UtilsService'];

	function CadastroUsuarioController($rootScope, $scope, UsuarioService, UtilsService) {

		var vm = this;

		vm.usuario = null;
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
			usuarioCadastro.telefones = [];
			usuarioCadastro.telefones[0] = {};
			usuarioCadastro.telefones[0].ddd = "";
			usuarioCadastro.telefones[0].telefone = "";

			vm.usuario = usuarioCadastro;

			$scope.tiposUsuarioConsulta = $rootScope.dominios.tiposUsuarioConsulta;
		};

		function cadastrarUsuario() {

			if(validarUsuario(this.usuario)) {
				var funcSucesso = function(data) {
					alert("Usuario cadastrado com sucesso");
					UtilsService.irPara("home-administrador");
				};

				UsuarioService.cadastrarUsuario(this.usuario, funcSucesso);
			}
			
		};

		function voltar() {
			UtilsService.irPara("listaTurnos");
		};

		function validarUsuario(usuarioDto) {
			return true;
		};

		inicializarCadastro();

	}

})();