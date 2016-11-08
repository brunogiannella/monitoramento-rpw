(function() {
	'use strict';

	angular
		.module('removerOcorrencia.controller', [])
		.controller('RemoverOcorrenciaController' , RemoverOcorrenciaController);

	RemoverOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'OcorrenciaService', 'UtilsService'];

	function RemoverOcorrenciaController($rootScope, $scope, $stateParams, OcorrenciaService, UtilsService) {

		

		function inicializar() {
			vm.idOcorrencia = $stateParams.idOcorrencia;
		};

		function removerOcorrencia() {
			var funcSucesso = function(data) {
				alert("Ocorrência removida com sucesso.")
				UtilsService.irPara("consultar-turno", {idTurno:$stateParams.idTurno});
			};

			OcorrenciaService.removerOcorrencia(vm.idOcorrencia, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-funcionario');
		}

		function voltar() {
			UtilsService.irPara("consultar-turno", {idTurno:$stateParams.idTurno});
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerOcorrencia = removerOcorrencia;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();