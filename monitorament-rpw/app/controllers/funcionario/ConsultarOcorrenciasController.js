(function() {
	'use strict';

	angular
		.module('consultarOcorrenciasController.controller', [])
		.controller('ConsultarOcorrenciasController' , ConsultarOcorrenciasController);

	ConsultarOcorrenciasController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function ConsultarOcorrenciasController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializar() {

		};
		
		var vm = this;
		inicializar();

	}

})();