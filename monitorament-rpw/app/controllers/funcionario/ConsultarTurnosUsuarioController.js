(function() {
	'use strict';

	angular
		.module('consultarTurnosUsuario.controller', [])
		.controller('ConsultarTurnosUsuarioController' , ConsultarTurnosUsuarioController);

	ConsultarTurnosUsuarioController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function ConsultarTurnosUsuarioController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.sair = UtilsService.logout;
		vm.removerOcorrencia = removerOcorrencia;
		vm.codigoTurno = $stateParams.idTurno;	
		vm.permiteEdicao = $stateParams.editar;

		function inicializar() {
			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnoConsulta = data;
				}

				UtilsService.desativarLoading();
			};

			TurnoService.consultarDetalheTurno(vm.codigoTurno, funcSucesso);
		}

		function removerOcorrencia(idOcorrencia, idTurno) {
			UtilsService.irPara("remover-ocorrencia", {idOcorrencia: idOcorrencia, idTurno:idTurno});
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