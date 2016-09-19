(function() {
	'use strict';

	angular
		.module('removerUsuario.controller', [])
		.controller('RemoverUsuarioController' , RemoverUsuarioController);

	RemoverUsuarioController.$inject = ['$rootScope', '$scope', '$stateParams', 'UsuarioService', 'UtilsService'];

	function RemoverUsuarioController($rootScope, $scope, $stateParams, UsuarioService, UtilsService) {

		

		function inicializar() {
			vm.loginUsuario = $stateParams.loginUsuario;
			vm.idUsuario = $stateParams.idUsuario;
		};

		function removerUsuario() {
			var funcSucesso = function(data) {
				alert("Usuário removido com sucesso");
				UtilsService.irPara("usuarios");
			};

			UsuarioService.removerUsuario(vm.idUsuario, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("usuarios");
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerUsuario = removerUsuario;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();