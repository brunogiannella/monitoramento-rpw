(function() {
	'use strict';

	angular
		.module('aprovarTurno.controller', [])
		.controller('AprovarTurnoController' , AprovarTurnoController);

	AprovarTurnoController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function AprovarTurnoController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		function aprovarTurno() {
			var funcSucesso = function(data) {
				alert("Turno aprovado com sucesso!");
				voltar();
			};

			TurnoService.aprovarTurno(vm.idTurno, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("turnos-pendentes");
		};
		
		var vm = this;
		vm.idTurno = $stateParams.idTurno;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.aprovarTurno = aprovarTurno;
		$scope.sair = UtilsService.logout;
		
	}

})();