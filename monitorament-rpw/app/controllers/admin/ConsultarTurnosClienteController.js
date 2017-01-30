(function() {
	'use strict';

	angular
		.module('consultarTurnosCliente.controller', [])
		.controller('ConsultarTurnosClienteController' , ConsultarTurnosClienteController);

	ConsultarTurnosClienteController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function ConsultarTurnosClienteController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		function inicializar() {
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
			vm.consumidor = $stateParams.consumidor;
			vm.tipoConsulta = $stateParams.tipoConsulta;

			var funcSucesso = function(data) {
				if(data != null && data.length > 0) {
					vm.turnos = data;
				}
			};

			if(vm.tipoConsulta == "ultimos10") {
				TurnoService.consultarUltimosDezTurnos(vm.idCliente, funcSucesso);
			} else if(vm.tipoConsulta == "em_andamento") {
				TurnoService.consultarTurnosAndamentoCliente(vm.idCliente, funcSucesso);
			}
			
		};

		function inicio() {
			if(vm.consumidor == "cliente") {
				UtilsService.irPara('home-cliente');
			} else {
				UtilsService.irPara('home-administrador');
			}	
		}

		function voltar() {
			if(vm.consumidor == "cliente") {
				UtilsService.irPara('home-cliente');
			} else {
				UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
			}
		};

		function visualizarTurno(id) {
			UtilsService.irPara('consultar-turno', {idTurno: id, editar: true, consumidor: 'admin'});
		}

		function aprovar(id) {
			UtilsService.irPara('aprovar-turno', {idTurno: id});
		}

		function enviar(id) {
			UtilsService.irPara('home-administrador');
		}

		function visualizarRelatorio(id) {
			UtilsService.irPara('imprimir-relatorio', {idTurno: id, consumidor:'cliente'});
		}


		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.visualizarTurno = visualizarTurno;
		vm.aprovar = aprovar;
		vm.enviar = enviar;
		vm.visualizarRelatorio = visualizarRelatorio;
		vm.turnoSelecionado = "";
		
		inicializar();

	}

})();