(function() {
	'use strict';

	angular
		.module('tipoOcorrencia.controller', [])
		.controller('TipoOcorrenciaController' , TipoOcorrenciaController);

	TipoOcorrenciaController.$inject = ['$rootScope', '$scope', 'TipoOcorrenciaService', 'UtilsService'];

	function TipoOcorrenciaController($rootScope, $scope, TipoOcorrenciaService, UtilsService) {

		var vm = this;
		vm.voltar = voltar;
		vm.cadastrar = cadastrar;
		vm.removerTipoOcorrencia = removerTipoOcorrencia;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			vm.tipoOcorrencia = null;
			consultarTiposOcorrencia();
		}

		function cadastrar() {
			UtilsService.irPara('cadastrar-tipo-ocorrencias');
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function consultarTiposOcorrencia() {
			var funcSucesso = function(data) {
				vm.tiposOcorrencia = data;
			};

			TipoOcorrenciaService.listarTiposOcorrencia(funcSucesso);
		}

		function removerTipoOcorrencia(idTipoOcorrencia, nomeTipoOcorrencia) {
			UtilsService.irPara('remover-tipo-ocorrencia', {idTipoOcorrencia:idTipoOcorrencia, nomeTipoOcorrencia:nomeTipoOcorrencia});
		}

		inicializar();

	}

})();