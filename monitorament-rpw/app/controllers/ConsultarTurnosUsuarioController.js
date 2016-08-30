(function() {
	'use strict';

	angular
		.module('consultarTurnosUsuario.controller', [])
		.controller('ConsultarTurnosUsuarioController' , ConsultarTurnosUsuarioController);

	ConsultarTurnosUsuarioController.$inject = ['$rootScope', 'TurnoService', 'UtilsService'];

	function ConsultarTurnosUsuarioController($rootScope, TurnoService, UtilsService) {

		function consultarTurnosUsuario() {
			var codigoUsuario = $rootScope.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					$scope.usuarioPossuiTurnosAbertos = true;
					$scope.turnosUsuarioConsultado = data;
				} else {
					$scope.usuarioPossuiTurnosAbertos = false;
				}
				
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

		function visualizarTurno(codigoTurno) {
			UtilsService.irPara("abrirNovoTurno", {codigoTurno : codigoTurno});
		};

		function abrirNovoTurno(codigoTurno) {
			UtilsService.irPara("abrirNovoTurno");
		};

		function fecharTurno(codigoTurno) {
			
			if (confirm('Deseja realmente finalizar este turno?')) {
				var funcSucesso = function(data) {
					consultarTurnosUsuario();
				};

				TurnoService.fecharTurno(codigoTurno, funcSucesso);
			}

		};

		
		consultarTurnosUsuario();

	}

})();