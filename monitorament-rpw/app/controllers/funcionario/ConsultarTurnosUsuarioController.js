(function() {
	'use strict';

	angular
		.module('consultarTurnosUsuario.controller', [])
		.controller('ConsultarTurnosUsuarioController' , ConsultarTurnosUsuarioController);

	ConsultarTurnosUsuarioController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function ConsultarTurnosUsuarioController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		var vm = this;
		vm.codigoTurno = $stateParams.idTurno;	

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
		
		inicializar();

	}

})();