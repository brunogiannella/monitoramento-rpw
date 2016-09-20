(function() {
	'use strict';

	angular
		.module('editarUsuario.controller', [])
		.controller('EditarUsuarioController' , EditarUsuarioController);

	EditarUsuarioController.$inject = ['$rootScope', '$scope', '$stateParams', 'UsuarioService', 'UtilsService'];

	function EditarUsuarioController($rootScope, $scope, $stateParams, UsuarioService, UtilsService) {

		$scope.sair = UtilsService.logout;

		var vm = this;

		vm.usuario = null;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.usuarioLogado = $rootScope.usuarioLogado;
		vm.cadastrarUsuario = cadastrarUsuario;

		function inicializarCadastro() {
			

			var funcSucesso = function(data, controller) {
				var usuarioCadastro = {};
				usuarioCadastro.id = data.id;
				usuarioCadastro.usuario = data.usuario;
				usuarioCadastro.senha = data.senha;
				usuarioCadastro.nome = data.nome;
				usuarioCadastro.email = data.email;
				usuarioCadastro.tipoUsuario ="";

				usuarioCadastro.endereco = {};
				if(data.endereco != null) {
					usuarioCadastro.endereco.id = data.endereco.id;
					usuarioCadastro.endereco.logradouro = data.endereco.logradouro;
					usuarioCadastro.endereco.bairro = data.endereco.bairro;
					usuarioCadastro.endereco.cidade = data.endereco.cidade;
					usuarioCadastro.endereco.estado ="";
					usuarioCadastro.endereco.cep = data.endereco.cep;
				}

				usuarioCadastro.idCliente ="";

				usuarioCadastro.telefone = {};
				if(data.telefone != null) {
					usuarioCadastro.telefone.ddd = data.telefone.ddd;
					usuarioCadastro.telefone.telefone = data.telefone.telefone;
				}
				

				controller.usuario = usuarioCadastro;
			};

			UsuarioService.consultarUsuario($stateParams.idUsuario, funcSucesso, vm);

			$scope.tiposUsuarioConsulta = $rootScope.dominios.tiposUsuarioConsulta;
			$scope.clienteConsulta = $rootScope.dominios.clienteConsulta;
		};

		function cadastrarUsuario() {

			if(validarUsuario(this.usuario)) {
				var funcSucesso = function(data) {
					alert("Usuario alterado com sucesso");
					UtilsService.irPara("usuarios");
				};

				UsuarioService.atualizarUsuario(this.usuario, funcSucesso);
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