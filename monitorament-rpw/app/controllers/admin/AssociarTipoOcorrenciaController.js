(function() {
	'use strict';

	angular
		.module('associarTipoOcorrencia.controller', [])
		.controller('AssociarTipoOcorrenciaController' , AssociarTipoOcorrenciaController);

	AssociarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function AssociarTipoOcorrenciaController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		

		function inicializar() {
			vm.tipoOcorrenciasConsulta = $rootScope.dominios.tipoOcorrenciasConsulta;
			vm.associarTipoOcorrencia = {};
			vm.associarTipoOcorrencia.idTipoOcorrencia = [];
			vm.associarTipoOcorrencia.idCliente = $stateParams.idCliente;
			vm.nomeCliente = $stateParams.nomeCliente;
			vm.idCliente = $stateParams.idCliente;
		};

		function associarOcorrencia() {

			if(validarAssociacao(this.associarTipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência associada com sucesso");
					UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
				};

				ClienteService.associarTipoOcorrencia(this.associarTipoOcorrencia, funcSucesso);
			}
			
		};

		function validarAssociacao(equipamentoDto) {

			for(var i = 0; i < vm.tipoOcorrenciasConsulta.length; i++) {
				if(vm.tipoOcorrenciasConsulta[i].selected == true) {
					vm.associarTipoOcorrencia.idTipoOcorrencia[vm.associarTipoOcorrencia.idTipoOcorrencia.length] = vm.tipoOcorrenciasConsulta[i].id;
				}
			}

			if(vm.associarTipoOcorrencia.idTipoOcorrencia.length == 0) {
				alert("Selecione pelo menos um tipo de ocorrência");
				return false;
			}

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