(function() {
	'use strict';

	angular
		.module('editarCamera.controller', [])
		.controller('EditarCameraController' , EditarCameraController);

	EditarCameraController.$inject = ['$rootScope', '$scope', '$stateParams', 'CameraService', 'UtilsService'];

	function EditarCameraController($rootScope, $scope, $stateParams, CameraService, UtilsService) {

		$scope.sair = UtilsService.logout;

		var vm = this;

		vm.camera = null;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.usuarioLogado = $rootScope.usuarioLogado;
		vm.cadastrarCamera = cadastrarCamera;

		function inicializarCadastro() {
			$scope.tipoCamerasConsulta = $rootScope.dominios.tipoCamerasConsulta
			vm.camera = $stateParams.camera;
			vm.step = 1;
		};

		function cadastrarCamera() {

			if(validarCamera(this.camera)) {
				var funcSucesso = function(data) {
					alert("Camera alterada com sucesso");
					UtilsService.irPara("clientes", {idCliente:$stateParams.camera.idCliente});
				};

				CameraService.atualizarCamera(this.camera, funcSucesso);
			}
			
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes", {idCliente:$stateParams.camera.idCliente});
		};

		function validarCamera(cameraDto) {
			return true;
		};

		inicializarCadastro();

	}

})();