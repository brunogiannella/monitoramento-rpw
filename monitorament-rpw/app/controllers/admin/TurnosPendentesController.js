(function() {
	'use strict';

	angular
		.module('turnosPendentes.controller', [])
		.controller('TurnosPendentesController' , TurnosPendentesController);

	TurnosPendentesController.$inject = ['$rootScope', '$scope', 'TurnoService', 'UtilsService'];

	function TurnosPendentesController($rootScope, $scope, TurnoService, UtilsService) {

		var vm = this;
		vm.voltar = voltar;
		vm.visualizarTurno = visualizarTurno;
		vm.aprovar = aprovar;
		vm.enviar = enviar;
		vm.tipoConsulta = "pendentes";
		vm.visualizarRelatorio = visualizarRelatorio;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			consultarTurnosPendentes();
		}

		function visualizarTurno(id) {
			UtilsService.irPara('consultar-turno', {idTurno: id, editar: true, consumidor: 'admin'});
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function aprovar(id) {
			UtilsService.irPara('aprovar-turno', {idTurno: id});
		}

		function enviar(id) {
			UtilsService.irPara('home-administrador');
		}

		function visualizarRelatorio(id) {
			UtilsService.irPara('imprimir-relatorio', {idTurno: id});
		}

		function consultarTurnosPendentes() {
			var funcSucesso = function(data) {
				vm.turnosPendentes = data;
			};

			TurnoService.consultarTurnosPendentes(funcSucesso);
		}

		inicializar();

	}

})();