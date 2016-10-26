(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastrarOcorrenciaController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializar() {
			UtilsService.ativarLoading();
			var codigoCliente = $rootScope.usuarioLogado.idCliente;

			var funcSucesso = function(data) {
				vm.tiposOcorrencia = data;
			};

			ClienteService.consultarTiposOcorrenciaCliente(codigoCliente, funcSucesso);
		};
		
		var vm = this;
		inicializar();

	}

})();