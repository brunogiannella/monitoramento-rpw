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

					var funcSucesso = function(dataRelatorio) {
						if(data != null) {
							UtilsService.irPara('imprimir-relatorio-mensal', {relatorio: dataRelatorio, consumidor: 'clienteExterno'});
							UtilsService.desativarLoading();
						} else {
							alert("Ocorreu um problema ao gerar o relatório mensal.");
						}
					};

					RelatorioService.consultarRelatorioMensal($location.search().mes, $location.search().ano, $location.search().idCliente, funcSucesso);
				} else {
					$scope.erro = true;
					$scope.mensagemErro = "Usuário ou senha inválidos";
				}

				
				
			};

			LoginService.realizarLogin(this.usuario, this.senha, funcSucesso);
		}

		
	}

})();