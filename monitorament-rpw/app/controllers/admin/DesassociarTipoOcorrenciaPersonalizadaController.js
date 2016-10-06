(function() {
	'use strict';

	angular
		.module('desassociarTipoOcorrenciaPersonalizada.controller', [])
		.controller('DesassociarTipoOcorrenciaPersonalizadaController' , DesassociarTipoOcorrenciaPersonalizadaController);

	DesassociarTipoOcorrenciaPersonalizadaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function DesassociarTipoOcorrenciaPersonalizadaController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		

		function inicializar() {
			vm.nomeTipoOcorrencia = $stateParams.nomeTipoOcorrencia;
			vm.idTipoOcorrencia = $stateParams.idTipoOcorrencia;
			vm.idCliente = $stateParams.idCliente;

			vm.associarTipoOcorrencia = {};
			vm.associarTipoOcorrencia.idCliente = $stateParams.idCliente;
			vm.associarTipoOcorrencia.idTipoOcorrencia = $stateParams.idTipoOcorrencia;
		};

		function desassociarTipoOcorrencia() {
			var funcSucesso = function(data) {
				alert("Tipo ocorrência desassociada com sucesso");
				UtilsService.irPara("clientes", {idCliente:$stateParams.idCliente});
			};

			ClienteService.desassociarTipoOcorrenciaPersonalizada(vm.associarTipoOcorrencia, funcSucesso);			
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
		vm.desassociarTipoOcorrencia = desassociarTipoOcorrencia;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();