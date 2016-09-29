(function() {
	'use strict';

	angular
		.module('usuario.controller', [])
		.controller('UsuarioController' , UsuarioController);

	UsuarioController.$inject = ['$rootScope', '$scope', 'UsuarioService', 'UtilsService'];

	function UsuarioController($rootScope, $scope, UsuarioService, UtilsService) {

		var vm = this;
		vm.voltar = voltar;
		vm.cadastrar = cadastrar;
		vm.removerUsuario = removerUsuario;
		vm.editarUsuario = editarUsuario;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			vm.usuario = null;
			consultarUsuarios();
		}

		function cadastrar() {
			UtilsService.irPara('cadastrar-usuario');
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function consultarUsuarios() {
			vm.edicao = true;

			var funcSucesso = function(data) {
				vm.usuarios = data;
			};

			UsuarioService.consultarUsuarios(funcSucesso);
		}

		function removerUsuario(idUsuario, loginUsuario) {
			UtilsService.irPara('remover-usuario', {idUsuario:idUsuario, loginUsuario:loginUsuario});
		}

		function editarUsuario(idUsuario) {

			var funcSucesso = function(data, controller) {
				var usuarioCadastro = {};
				usuarioCadastro.id = data.id;
				usuarioCadastro.usuario = data.usuario;
				usuarioCadastro.senha = data.senha;
				usuarioCadastro.nome = data.nome;
				usuarioCadastro.email = data.email;
				usuarioCadastro.tipoUsuario = data.tipoUsuario;

				usuarioCadastro.endereco = {};
				if(data.endereco != null) {
					usuarioCadastro.endereco.id = data.endereco.id;
					usuarioCadastro.endereco.logradouro = data.endereco.logradouro;
					usuarioCadastro.endereco.bairro = data.endereco.bairro;
					usuarioCadastro.endereco.cidade = data.endereco.cidade;
					usuarioCadastro.endereco.estado = data.endereco.estado;
					usuarioCadastro.endereco.cep = data.endereco.cep;
				}

				if(data.cliente != null) {
					usuarioCadastro.idCliente = data.cliente.id;
				}
				
				usuarioCadastro.telefone = {};
				if(data.telefone != null) {
					usuarioCadastro.telefone.id = data.telefone.id;
					usuarioCadastro.telefone.ddd = data.telefone.ddd;
					usuarioCadastro.telefone.telefone = data.telefone.telefone;
				}
				
				UtilsService.irPara('editar-usuario', {usuario:usuarioCadastro});
			};

			UsuarioService.consultarUsuario(idUsuario, funcSucesso, $scope);
		}

		inicializar();

	}

})();