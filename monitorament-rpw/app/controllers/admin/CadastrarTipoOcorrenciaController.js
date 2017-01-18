(function() {
	'use strict';

	angular
		.module('cadastrarTipoOcorrenciaController.controller', [])
		.controller('CadastrarTipoOcorrenciaController' , CadastrarTipoOcorrenciaController);

	CadastrarTipoOcorrenciaController.$inject = ['$rootScope', '$scope', 'ClienteService', 'UtilsService', 'TipoOcorrenciaService'];

	function CadastrarTipoOcorrenciaController($rootScope, $scope, ClienteService, UtilsService, TipoOcorrenciaService) {

		

		function inicializar() {
			$scope.tiposCampoOcorrenciaConsulta = $rootScope.dominios.tiposCampoOcorrenciaConsulta;
		};

		function cadastrarTipoOcorrencia() {

			if(validarTipoOcorrencia(this.tipoOcorrencia)) {
				var funcSucesso = function(data) {
					var funcSucessoTiposCampoOcorrencia = function(data) {
						$rootScope.dominios.tipoOcorrenciasConsulta = data;
						alert("Tipo ocorrência cadastrada com sucesso");
						UtilsService.irPara("tipos-ocorrencia");
					};

					TipoOcorrenciaService.listarTiposOcorrencia(funcSucessoTiposCampoOcorrencia);
				};

				TipoOcorrenciaService.cadastrarTipoOcorrencia(this.tipoOcorrencia, funcSucesso);
			}
			
		};

		function validarTipoOcorrencia(tipoOcorrenciaDto) {

			var total = 0;
			for(var i = 0; i < tipoOcorrenciaDto.campos.length; i++) {
				total = total + parseInt(tipoOcorrenciaDto.campos[i].porcentagemColuna);
			}

			if(total != 100) {
				alert("A some das colunas tamanho (%) devem obrigatóriamente ser igual a 100.");
				return false;
			}

			return true;
		};

		function inicio() {
			UtilsService.irPara('home-administrador');
		}

		function voltar() {
			UtilsService.irPara("tipos-ocorrencia");
		};


		
		var vm = this;
		vm.tipoOcorrencia = {};
		vm.tipoOcorrencia.campos = [];
		vm.voltar = voltar;
		vm.inicio = inicio;
		$scope.sair = UtilsService.logout;
		vm.cadastrarTipoOcorrencia = cadastrarTipoOcorrencia;

		inicializar();

	}

})();