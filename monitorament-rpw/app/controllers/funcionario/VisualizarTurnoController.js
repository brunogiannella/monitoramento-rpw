(function() {
	'use strict';

	angular
		.module('visualizarTurnoController.controller', [])
		.controller('VisualizarTurnoController' , VisualizarTurnoController);

	VisualizarTurnoController.$inject = ['$rootScope', '$stateParams', 'TurnoService', 'UtilsService'];

	function VisualizarTurnoController($rootScope, $stateParams, TurnoService, UtilsService) {

		this.codigoTurno = $stateParams.codigoTurno;

		function visualizarTurno(codigoTurno) {

			var funcSucesso = function(data) {
				$scope.turnoConsultado = data;
				UtilsService.irPara("visualizarTurno");
			};

			TurnoService.consultarTurno(codigoTurno, funcSucesso);
		};

		function voltar() {
			UtilsService.irPara("listaTurnos");
		}

		function fecharTurno(codigoTurno) {
			
			if (confirm('Deseja realmente finalizar este turno?')) {
				var funcSucesso = function(data) {
					consultarTurnosUsuario();
				};

				TurnoService.fecharTurno(codigoTurno, funcSucesso);
			}

		};

		visualizarTurno(this.codigoTurno);

	}

})();