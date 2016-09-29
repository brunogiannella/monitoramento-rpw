(function() {
	'use strict';

	angular
		.module('removerTipoOcorrencia.controller', [])
		.controller('RemoverTipoOcorrenciaController' , RemoverTipoOcorrenciaController);

	RemoverTipoOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'TipoOcorrenciaService', 'UtilsService'];

	function RemoverTipoOcorrenciaController($rootScope, $scope, $stateParams, TipoOcorrenciaService, UtilsService) {

		

		function inicializar() {
			vm.nomeTipoOcorrencia = $stateParams.nomeTipoOcorrencia;
			vm.idTipoOcorrencia = $stateParams.idTipoOcorrencia;
		};

		function removerTipoOcorrencia() {
			var funcSucesso = function(data) {
				alert("Tipo ocorrência removido com sucesso");
				UtilsService.irPara("tipos-ocorrencia");
			};

			TipoOcorrenciaService.removerTipoOcorrencia(vm.idTipoOcorrencia, funcSucesso);			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("tipos-ocorrencia");
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerTipoOcorrencia = removerTipoOcorrencia;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();