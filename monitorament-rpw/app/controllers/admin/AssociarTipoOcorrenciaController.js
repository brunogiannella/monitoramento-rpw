(function() {
	'use strict';

	angular
		.module('associarTipoOcorrencia.controller', [])
		.controller('AssociarTipoOcorrenciaController' , AssociarTipoOcorrenciaController);

	AssociarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'TipoOcorrenciaService', 'UtilsService'];

	function AssociarTipoOcorrenciaController($rootScope, $scope, $stateParams, TipoOcorrenciaService, UtilsService) {

		

		function inicializar() {
			$scope.tipoOcorrenciasConsulta = $rootScope.dominios.tipoOcorrenciasConsulta;
			vm.associarTipoOcorrencia = {};
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function associarOcorrencia() {

			if(validarAssociacao(this.tipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência cadastrada com sucesso");
					UtilsService.irPara("home-administrador");
				};

				TipoOcorrenciaService.cadastrarTipoOcorrencia(this.tipoOcorrencia, funcSucesso);
			}
			
		};

		function validarAssociacao(equipamentoDto) {
			return true;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.associarOcorrencia = associarOcorrencia;
		
		inicializar();

	}

})();