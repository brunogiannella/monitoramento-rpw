(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'OcorrenciaService', 'UtilsService', 'TurnoService'];

	function CadastrarOcorrenciaController($rootScope, $scope, $stateParams, ClienteService, OcorrenciaService, UtilsService, TurnoService) {

		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.consultarTipoOcorrencia = consultarTipoOcorrencia;
		vm.salvarOcorrencia = salvarOcorrencia;
		vm.campos = null;
		vm.indexTipoOcorrenciaSelecionada = null;
		vm.camerasCliente = $rootScope.clienteFuncionario.cameras;
		$scope.sair = UtilsService.logout;

		function inicializar() {

			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnoConsulta = data;
				}

				UtilsService.desativarLoading();
			};

			TurnoService.consultarDetalheTurno($stateParams.codigoTurno, funcSucesso);

			vm.tiposOcorrencia = $stateParams.tiposOcorrencia;
			vm.informantesOcorrencias = $rootScope.dominios.informantesOcorrencias;

			vm.ocorrencia = {};
			vm.ocorrencia.campos = [];
		};

		function consultarTipoOcorrencia() {
			vm.tipoOcorrencia = vm.tiposOcorrencia[parseInt(vm.indexTipoOcorrenciaSelecionada)];
		}

		function salvarOcorrencia() {
			

			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					vm.ocorrencia.idTipoOcorrencia = vm.tipoOcorrencia.id;
					vm.ocorrencia.idCliente = $rootScope.clienteFuncionario.id;
					vm.ocorrencia.idTurno = data[0].id;
					vm.ocorrencia.codigoUsuario = $rootScope.usuarioLogado.idUsuario;

					var funcSucesso = function(data) {
						alert("Ocorrência cadastrada com sucesso.");
						UtilsService.desativarLoading();
						resetCadastro();
					};

					OcorrenciaService.cadastrarOcorrencia(vm.ocorrencia, funcSucesso);
				} else {
					alert("No momento seu usuário não possui um turno aberto. Para cadastrar uma avaliação da câmera abra um novo turno.")
				}
				
				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		}

		function voltar() {
			UtilsService.irPara("home-funcionario");
		}

		function inicio() {
			UtilsService.irPara("home-funcionario");
		}

		function resetCadastro() {
			inicializar();
			vm.tipoOcorrencia = null;
			vm.ocorrencia = {};
			vm.ocorrencia.campos = [];
		}
		
		var vm = this;
		inicializar();

	}

})();