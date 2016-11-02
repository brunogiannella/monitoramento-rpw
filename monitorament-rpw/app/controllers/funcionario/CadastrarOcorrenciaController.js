(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'OcorrenciaService', 'UtilsService', 'TurnoService'];

	function CadastrarOcorrenciaController($rootScope, $scope, $stateParams, ClienteService, OcorrenciaService, UtilsService, TurnoService) {

		this.voltar = voltar;
		this.inicio = inicio;
		this.consultarTipoOcorrencia = consultarTipoOcorrencia;
		this.salvarOcorrencia = salvarOcorrencia;
		this.campos = null;
		this.indexTipoOcorrenciaSelecionada = null;
		$scope.sair = UtilsService.logout;

		function inicializar() {
			vm.tiposOcorrencia = $stateParams.tiposOcorrencia;

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
						UtilsService.irPara("home-funcionario");
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
		
		var vm = this;
		inicializar();

	}

})();