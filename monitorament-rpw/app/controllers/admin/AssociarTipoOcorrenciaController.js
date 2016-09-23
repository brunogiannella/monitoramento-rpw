(function() {
	'use strict';

	angular
		.module('associarTipoOcorrencia.controller', [])
		.controller('AssociarTipoOcorrenciaController' , AssociarTipoOcorrenciaController);

	AssociarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function AssociarTipoOcorrenciaController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		

		function inicializar() {
			$scope.tipoOcorrenciasConsulta = $rootScope.dominios.tipoOcorrenciasConsulta;
			vm.associarTipoOcorrencia = {};
			vm.associarTipoOcorrencia.idCliente = $stateParams.idCliente;
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function associarOcorrencia() {

			if(validarAssociacao(this.associarTipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência associada com sucesso");
					UtilsService.irPara("clientes");
				};

				ClienteService.associarTipoOcorrencia(this.associarTipoOcorrencia, funcSucesso);
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