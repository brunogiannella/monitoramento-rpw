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
			vm.usuario = $stateParams.usuario;
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