(function() {
	'use strict';

	angular
		.module('associarTipoOcorrenciaPersonalizada.controller', [])
		.controller('AssociarTipoOcorrenciaPersonalizadaController' , AssociarTipoOcorrenciaPersonalizadaController);

	AssociarTipoOcorrenciaPersonalizadaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function AssociarTipoOcorrenciaPersonalizadaController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		

		function inicializar() {
			$scope.tipoOcorrenciasConsulta = $rootScope.dominios.tipoOcorrenciasPersonalizadasConsulta;
			vm.associarTipoOcorrencia = {};
			vm.associarTipoOcorrencia.idCliente = $stateParams.idCliente;
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function associarOcorrencia() {

			if(validarAssociacao(this.associarTipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência personalizada associada com sucesso");
					UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
				};

				ClienteService.associarTipoOcorrenciaPersonalizada(this.associarTipoOcorrencia, funcSucesso);
			}
			
		};

		function validarAssociacao(equipamentoDto) {
			return true;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.associarOcorrencia = associarOcorrencia;
		
		inicializar();

	}

})();