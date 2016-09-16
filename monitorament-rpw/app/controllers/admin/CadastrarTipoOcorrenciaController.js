(function() {
	'use strict';

	angular
		.module('cadastrarTipoOcorrenciaController.controller', [])
		.controller('CadastrarTipoOcorrenciaController' , CadastrarTipoOcorrenciaController);

	CadastrarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService', 'TipoOcorrenciaService'];

	function CadastrarTipoOcorrenciaController($rootScope, $scope, ClienteService, UtilsService, TipoOcorrenciaService) {

		

		function inicializar() {
			$scope.tiposCampoOcorrenciaConsulta = $rootScope.dominios.tiposCampoOcorrenciaConsulta;
		};

		function cadastrarTipoOcorrencia() {

			if(validarTipoOcorrencia(this.tipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência cadastrada com sucesso");
					UtilsService.irPara("home-administrador");
				};

				TipoOcorrenciaService.cadastrarTipoOcorrencia(this.tipoOcorrencia, funcSucesso);
			}
			
		};

		function validarTipoOcorrencia(tipoOcorrenciaDto) {
			return true;
		};

		
		var vm = this;
		vm.tipoOcorrencia = {};
		vm.tipoOcorrencia.campos = [];
		vm.cadastrarTipoOcorrencia = cadastrarTipoOcorrencia;

		inicializar();

	}

})();