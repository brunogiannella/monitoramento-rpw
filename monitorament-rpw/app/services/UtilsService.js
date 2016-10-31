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

		return {
			irPara : irPara,
			getToken : getToken,
			logout : logout,
			ativarLoading : ativarLoading,
			desativarLoading : desativarLoading,
			tratarErrosHttp : tratarErrosHttp
		}
	}

})();