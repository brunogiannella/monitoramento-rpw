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

		inicializar();

	}

})();