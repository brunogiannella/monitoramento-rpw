(function() {
	'use strict';

	angular
		.module('imprimirRelatorioClienteController.controller', [])
		.controller('ImprimirRelatorioClienteController' , ImprimirRelatorioClienteController);

	ImprimirRelatorioClienteController.$inject = ['$scope', '$location', '$rootScope', 'LoginService', 'UtilsService', 'RelatorioService'];

	function ImprimirRelatorioClienteController($scope, $location, $rootScope, LoginService, UtilsService, RelatorioService) {

		this.usuario = null;
		this.senha = null;
		this.imprimir = imprimir;

		function imprimir() {
			UtilsService.ativarLoading();
			$scope.erro = false;
			$scope.mensagemErro = "";

			var funcSucesso = function(data) {

				if(data != null) {

					$rootScope.usuarioLogado = data;

					if($location.search().tipo == 'diario') {
						UtilsService.irPara('imprimir-relatorio-diario', {idTurno: $location.search().id_turno});
					} else if($location.search().tipo == 'mensal') {
						var funcSucesso = function(dataRelatorio) {
							if(data != null) {
								UtilsService.irPara('imprimir-relatorio-mensal', {relatorio: dataRelatorio});
								UtilsService.desativarLoading();
							} else {
								alert("Ocorreu um problema ao gerar o relatório mensal.");
								UtilsService.desativarLoading();
								UtilsService.irPara('imprimir-relatorio-cliente');
							}
						};

						RelatorioService.consultarRelatorioMensal($location.search().mes, $location.search().ano, $location.search().id_cliente, funcSucesso);
					} else {
						alert("Tipo de relatório inválido");
						UtilsService.desativarLoading();
						UtilsService.irPara('imprimir-relatorio-cliente');
					}
					
				} else {
					$scope.erro = true;
					$scope.mensagemErro = "Usuário ou senha inválidos";
				}

				
				
			};

			LoginService.realizarLogin(this.usuario, this.senha, funcSucesso);
		}
	}

})();