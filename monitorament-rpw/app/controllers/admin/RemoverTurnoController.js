(function() {
	'use strict';

	angular
		.module('removerTurno.controller', [])
		.controller('RemoverTurnoController' , RemoverTurnoController);

	RemoverTurnoController.$inject = ['$rootScope', '$scope', '$stateParams', 'TurnoService', 'UtilsService'];

	function RemoverTurnoController($rootScope, $scope, $stateParams, TurnoService, UtilsService) {

		

		function inicializar() {
			vm.idTurno = $stateParams.idTurno;
		};

		function removerTurno() {
			var funcSucesso = function(data) {
				alert("Turno removido com sucesso");
				UtilsService.irPara("home-administrador");
			};

			TurnoService.removerTurno(vm.idTurno, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerTurno = removerTurno;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();