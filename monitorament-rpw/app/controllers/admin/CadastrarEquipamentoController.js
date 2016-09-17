(function() {
	'use strict';

	angular
		.module('cadastrarEquipamento.controller', [])
		.controller('CadastrarEquipamentoController' , CadastrarEquipamentoController);

	CadastrarEquipamentoController.$inject = ['$rootScope', '$scope', '$stateParams', 'CameraService', 'UtilsService', 'TipoOcorrenciaService'];

	function CadastrarEquipamentoController($rootScope, $scope, $stateParams, CameraService, UtilsService, TipoOcorrenciaService) {

		

		function inicializar() {
			$scope.tipoCamerasConsulta = $rootScope.dominios.tipoCamerasConsulta
		};

		function cadastrarEquipamento() {

			if(validarEquipamento(this.tipoOcorrencia)) {
				var funcSucesso = function(data) {
					alert("Tipo ocorrência cadastrada com sucesso");
					UtilsService.irPara("home-administrador");
				};

				TipoOcorrenciaService.cadastrarTipoOcorrencia(this.tipoOcorrencia, funcSucesso);
			}
			
		};

		function validarEquipamento(equipamentoDto) {
			return true;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("clientes");
		};


		
		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.cadastrarEquipamento = cadastrarEquipamento;
		vm.nomeCliente = $stateParams.nomeCliente;
		vm.idCliente = $stateParams.idCliente;

		inicializar();

	}

})();