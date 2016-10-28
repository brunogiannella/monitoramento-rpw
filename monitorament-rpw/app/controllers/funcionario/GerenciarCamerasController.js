(function() {
	'use strict';

	angular
		.module('gerenciarCamerasController.controller', [])
		.controller('GerenciarCamerasController' , GerenciarCamerasController);

	GerenciarCamerasController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService'];

	function GerenciarCamerasController($rootScope, $scope, ClienteService, UtilsService) {

		this.voltar = voltar;
		this.inicio = inicio;

		function inicializar() {
			vm.camerasCliente = $rootScope.clienteFuncionario.cameras;
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