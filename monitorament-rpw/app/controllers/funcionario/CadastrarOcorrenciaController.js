(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function CadastrarOcorrenciaController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializar() {

		};
		
		var vm = this;
		inicializar();

	}

})();