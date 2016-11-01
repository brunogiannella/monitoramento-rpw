(function() {
	'use strict';

	angular
		.module('cadastrarOcorrenciaController.controller', [])
		.controller('CadastrarOcorrenciaController' , CadastrarOcorrenciaController);

	CadastrarOcorrenciaController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService', 'TurnoService'];

	function CadastrarOcorrenciaController($rootScope, $scope, $stateParams, ClienteService, UtilsService, TurnoService) {

		this.voltar = voltar;
		this.inicio = inicio;
		this.consultarTipoOcorrencia = consultarTipoOcorrencia;
		this.salvarOcorrencia = salvarOcorrencia;
		this.campos = null;
		this.indexTipoOcorrenciaSelecionada = null;
		$scope.sair = UtilsService.logout;

		function inicializar() {
			vm.tiposOcorrencia = $stateParams.tiposOcorrencia;
		};

		function consultarTipoOcorrencia() {
			vm.tipoOcorrencia = vm.tiposOcorrencia[parseInt(vm.indexTipoOcorrenciaSelecionada)];
		}

		function salvarOcorrencia() {
			

			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					var ocorrencia = {};
					ocorrencia.idTipoOcorrencia = vm.tipoOcorrencia.id;
					ocorrencia.idCliente = $rootScope.clienteFuncionario.id;
					ocorrencia.idTurno = data[0].id;
					ocorrencia.campos = vm.campos;

					alert(ocorrencia);
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