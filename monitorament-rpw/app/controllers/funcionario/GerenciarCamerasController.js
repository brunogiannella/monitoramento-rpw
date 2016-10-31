(function() {
	'use strict';

	angular
		.module('gerenciarCamerasController.controller', [])
		.controller('GerenciarCamerasController' , GerenciarCamerasController);

	GerenciarCamerasController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'CameraService', 'UtilsService'];

	function GerenciarCamerasController($rootScope, $scope, $stateParams, ClienteService, CameraService, UtilsService) {

		this.voltar = voltar;
		this.inicio = inicio;
		this.inserirAvalicacaoDesligada = inserirAvalicacaoDesligada;
		this.reativarCamera = reativarCamera;
		this.situacaoCameraDto = {};

		function inicializar() {
			vm.situacaoCameraDto.idTurno = $stateParams.idTurno;
			vm.situacaoCameraDto.idCliente = $rootScope.clienteFuncionario.id;

			vm.camerasCliente = $rootScope.clienteFuncionario.cameras;

			UtilsService.ativarLoading();
			var funcSucesso = function(data) {
				if(data != null && data.length > 0) {

					$scope.camerasDesligadas = data;
				}

				UtilsService.desativarLoading();
			};

			CameraService.listarCamerasInativasCliente($rootScope.clienteFuncionario.id, funcSucesso);
		};
		
		function inserirAvalicacaoDesligada() {
			vm.situacaoCameraDto.ligada = false;

			UtilsService.ativarLoading();
			var funcSucesso = function(data) {
				var funcSucesso = function(data) {
					if(data != null && data.length > 0) {
						alert("Avaliação inserida com sucesso.");
						vm.situacaoCameraDto.dataHora = null;
						$scope.camerasDesligadas = data;
					}

					UtilsService.desativarLoading();
				};

				CameraService.listarCamerasInativasCliente($rootScope.clienteFuncionario.id, funcSucesso);
			};

			CameraService.inserirAvaliacaoCamera(vm.situacaoCameraDto, funcSucesso);
		}

		function reativarCamera(idSituacaoCamera) {
			vm.situacaoCameraLigadaDto = {};
			vm.situacaoCameraLigadaDto.idSituacaoCamera = idSituacaoCamera;
			vm.situacaoCameraLigadaDto.dataHora = vm.dataAvaliacaoLigada;

			UtilsService.ativarLoading();
			var funcSucesso = function(data) {
				var funcSucesso = function(data) {
					alert("Camera restaurada com sucesso.");
					vm.situacaoCameraDto.dataHora = null;
					vm.dataAvaliacaoLigada = null;
					$scope.camerasDesligadas = data;

					UtilsService.desativarLoading();
				};

				CameraService.listarCamerasInativasCliente($rootScope.clienteFuncionario.id, funcSucesso);
			};

			CameraService.inserirAvaliacaoCamera(vm.situacaoCameraLigadaDto, funcSucesso);
		}

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