(function() {
	'use strict';

	angular
		.module('iniciarNovoTurnoController.controller', [])
		.controller('IniciarNovoTurnoController' , IniciarNovoTurnoController);

	IniciarNovoTurnoController.$inject = ['$rootScope', '$scope', 'TurnoService', 'UtilsService'];

	function IniciarNovoTurnoController($rootScope, $scope, TurnoService, UtilsService) {

		this.voltar = voltar;
		this.inicio = inicio;
		this.iniciarNovoTurno = iniciarNovoTurno;
		$scope.sair = UtilsService.logout;
		this.turno = {};
		var vm = this;

		function iniciliazarProcessoTurno() {
			vm.turno.dataInicio = "";
			vm.turno.nomeCliente = $rootScope.usuarioLogado.nomeCliente;
			vm.turno.idCliente = $rootScope.usuarioLogado.idCliente;
			vm.turno.periodo = "";
			vm.turno.condicaoClimatica = "";
			vm.turno.tempo = "";
			vm.turno.operadores = [];
			vm.turno.idUsuario = $rootScope.usuarioLogado.idUsuario;
			vm.turno.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;

			vm.condicoesClimaticas = $rootScope.dominios.condicoesClimaticas;
			vm.periodos = $rootScope.dominios.periodos;
			vm.tempo = $rootScope.dominios.tempo;
		}

		function iniciarNovoTurno() {

			if(validarTurno(vm.turno)) {
				var funcSucesso = function(data) {
					alert("Turno aberto com sucesso");
					UtilsService.irPara("home-funcionario");
				};

				TurnoService.iniciarTurno(vm.turno, funcSucesso);
			}
			
		};

		function voltar() {
			UtilsService.irPara("home-funcionario");
		}

		function inicio() {
			UtilsService.irPara("home-funcionario");
		}

		function validarTurno(turnoDto) {
			return true;
		}


		iniciliazarProcessoTurno();

	}

})();