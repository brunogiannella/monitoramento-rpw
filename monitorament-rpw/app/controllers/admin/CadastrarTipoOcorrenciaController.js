(function() {
	'use strict';

	angular
		.module('cadastrarTipoOcorrenciaController.controller', [])
		.controller('CadastrarTipoOcorrenciaController' , CadastrarTipoOcorrenciaController);

	CadastrarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastrarTipoOcorrenciaController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializar() {

		};
		
		var vm = this;
		inicializar();

	}

})();