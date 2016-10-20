(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', 'UtilsService', 'TurnoService'];

	function HomeController($rootScope, UtilsService, TurnoService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
		this.sair = UtilsService.logout;

		this.turnos = function() {
			var codigoUsuario = $rootScope.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					$scope.turnosUsuarioConsultado = data;
					alert("No momento seu usuário possui um turno aberto, é necessário fecha-lo antes de abrir novo turno.")
				} else {
					UtilsService.irPara("cadastrar-turno");
				}
				
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

	}

})();