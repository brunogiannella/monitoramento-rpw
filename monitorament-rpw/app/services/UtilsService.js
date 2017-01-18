(function() {

	'use strict';


	angular
		.module('utils.service', [])
		.factory('UtilsService', UtilsService);

	UtilsService.$inject = ['$rootScope', '$state'];

	function UtilsService($rootScope, $state) {

		function ativarLoading() {
			$rootScope.loadingRodando = true;
		}

		function desativarLoading() {
			$rootScope.loadingRodando = false;
		}

		function irPara(tela, parametro) {
			$state.go(tela, parametro);
		}

		function logout() {
			$rootScope.usuarioLogado = null;
			irPara('login');
		}

		function getToken() {
			if($rootScope.usuarioLogado != null) {
				return $rootScope.usuarioLogado.token;
			}

			return null;
		};

		function tratarErrosHttp(response) {
			desativarLoading();
			alert("Ocorreu um problema durante a operação. Tente novamente por favor.");
		}

		function getMonth(mes) {
			if(mes == 1) {
				return "Janeiro";
			} else if(mes == 2) {
				return "Fevereiro";
			} else if(mes == 3) {
				return "Março";
			} else if(mes == 4) {
				return "Abril";
			} else if(mes == 5) {
				return "Maio";
			} else if(mes == 6) {
				return "Junho";
			} else if(mes == 7) {
				return "Julho";
			} else if(mes == 8) {
				return "Agosto";
			} else if(mes == 9) {
				return "Setembro";
			} else if(mes == 10) {
				return "Outubro";
			} else if(mes == 11) {
				return "Novembro";
			} else if(mes == 12) {
				return "Janeiro";
			}
		}

		return {
			irPara : irPara,
			getToken : getToken,
			logout : logout,
			ativarLoading : ativarLoading,
			desativarLoading : desativarLoading,
			tratarErrosHttp : tratarErrosHttp,
			getMonth : getMonth
		}
	}

})();