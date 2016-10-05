(function() {
	'use strict';

	angular
		.module('removerCamera.controller', [])
		.controller('RemoverCameraController' , RemoverCameraController);

	RemoverCameraController.$inject = ['$rootScope', '$scope', '$stateParams', 'CameraService', 'UtilsService'];

	function RemoverCameraController($rootScope, $scope, $stateParams, CameraService, UtilsService) {

		

		function inicializar() {
			vm.nomeCamera = $stateParams.nomeCamera;
			vm.idCamera = $stateParams.idCamera;
		};

		function removerCamera() {
			var funcSucesso = function(data) {
				alert("Camera removido com sucesso");
				UtilsService.irPara("clientes");
			};

			CameraService.removerCamera(vm.idCamera, funcSucesso);			
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
		vm.removerCamera = removerCamera;
		$scope.sair = UtilsService.logout;
		

		inicializar();

	}

})();