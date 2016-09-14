(function() {
	'use strict';

	angular
		.module('gerenciarCamerasController.controller', [])
		.controller('GerenciarCamerasController' , GerenciarCamerasController);

	GerenciarCamerasController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function GerenciarCamerasController($rootScope, $scope, ClienteService, UtilsService) {

		

		function inicializar() {

		};
		
		var vm = this;
		inicializar();

	}

})();