(function() {
	'use strict';

	angular
		.module('finalizarTurno.controller', [])
		.controller('FinalizarTurnoController' , FinalizarTurnoController);

	FinalizarTurnoController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function FinalizarTurnoController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		var vm = this;
		vm.sair = UtilsService.logout;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.finalizarTurno = finalizarTurno;
		

		function inicializar() {
			vm.codigoTurno = $stateParams.idTurno;	
			vm.dataInicio = $stateParams.dataInicio;

			vm.turnoDto = {};
			vm.turnoDto.id	= vm.codigoTurno;
			vm.turnoDto.dataFim = "";
		}

		function finalizarTurno() {
			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				alert("Turno finalizado com sucesso, o mesmo será enviado para validação.");
				UtilsService.desativarLoading();
				UtilsService.irPara("orientacoes-envio", {idTurno: vm.codigoTurno});
			};

			TurnoService.fecharTurno(vm.turnoDto, funcSucesso);
		}

		function voltar() {
			UtilsService.irPara("home-funcionario");
		}

		function inicio() {
			UtilsService.irPara("home-funcionario");
		}
		
		inicializar();

	}

})();