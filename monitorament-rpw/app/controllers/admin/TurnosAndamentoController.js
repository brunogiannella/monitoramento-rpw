(function() {
	'use strict';

	angular
		.module('turnosAndamento.controller', [])
		.controller('TurnosAndamentoController' , TurnosAndamentoController);

	TurnosAndamentoController.$inject = ['$rootScope', '$scope', 'TurnoService', 'UtilsService'];

	function TurnosAndamentoController($rootScope, $scope, TurnoService, UtilsService) {

		var vm = this;
		vm.voltar = voltar;
		vm.tipoConsulta = "andamento";
		vm.visualizarTurno = visualizarTurno;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			consultarTurnosAndamento();
		}

		function visualizarTurno(id) {
			UtilsService.irPara('consultar-turno', {idTurno: id, editar: true, consumidor: 'admin'});
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function consultarTurnosAndamento() {
			var funcSucesso = function(data) {
				vm.turnosPendentes = data;
			};

			TurnoService.consultarTurnosAndamento(funcSucesso);
		}

		inicializar();

	}

})();