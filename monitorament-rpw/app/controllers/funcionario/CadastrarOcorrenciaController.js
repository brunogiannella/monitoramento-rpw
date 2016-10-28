(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastrarOcorrenciaController($rootScope, $scope, ClienteService, UtilsService) {

		this.voltar = voltar;
		this.inicio = inicio;
		$scope.sair = UtilsService.logout;

		function inicializar() {
			UtilsService.ativarLoading();
			var codigoCliente = $rootScope.usuarioLogado.idCliente;

			var funcSucesso = function(data) {
				vm.tiposOcorrencia = data;
			};

			ClienteService.consultarTiposOcorrenciaCliente(codigoCliente, funcSucesso);
		};

		function voltar() {
			UtilsService.irPara("home-funcionario");
		}

		function inicio() {
			UtilsService.irPara("home-funcionario");
		}
		
		var vm = this;
		inicializar();

	}

})();