(function() {
	'use strict';

	angular
		.module('iniciarNovoTurnoController.controller', [])
		.controller('IniciarNovoTurnoController' , IniciarNovoTurnoController);

	IniciarNovoTurnoController.$inject = ['$rootScope', 'TurnoService', 'UtilsService'];

	function IniciarNovoTurnoController($rootScope, $stateParams, TurnoService, UtilsService) {

		this.turno = new Object();

		this.usuario = $rootScope.usuarioLogado;
		this.clientes = null;

		function iniciliazarProcessoTurno() {
			this.turno.dataInicio = "";
			this.turno.dataFim = "";
			this.turno.nomeCliente = "";
			this.turno.idCliente ="";
			this.turno.periodo = "";
			this.turno.condicaoClimatica = "";
			this.turno.tempo = "";

			var funcSucessoPeriodos = function(data) {
				$scope.periodosConsulta = data;
			};

			var funcSucessoCondicoesClimaticas = function(data) {
				$scope.condicoesClimaticasConsulta = data;
			};

			var funcSucessoTempos = function(data) {
				$scope.temposConsulta = data;
			};

			TurnoService.consultarPeriodos(funcSucessoPeriodos);
			TurnoService.consultarCondicoesClimaticas(funcSucessoCondicoesClimaticas);
			TurnoService.consultarTempos(funcSucessoTempos);

		}

		function iniciarNovoTurno() {

			if(validarTurno(this.turno)) {
				var funcSucesso = function(data) {
					alert("Turno aberto com sucesso");
					UtilsService.irPara("listaTurnos");
				};

				TurnoService.iniciarTurno(this.turno, funcSucesso);
			}
			
		};

		function voltar() {
			UtilsService.irPara("listaTurnos");
		}

		function validarTurno(turnoDto) {
			return true;
		}

	}

})();