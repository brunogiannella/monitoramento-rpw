(function() {

	'use strict';


	angular
		.module('utils.service', [])
		.factory('UtilsService', UtilsService);

	UtilsService.$inject = ['$rootScope', '$state'];

	function UtilsService($rootScope, $state) {

		function irPara(tela, parametro) {
			$state.go(tela, parametro);
		}

		function getToken() {
			if($rootScope.usuarioLogado != null) {
				return $rootScope.usuarioLogado.token;
			}

			return null;
		};

		return {
			irPara : irPara,
			getToken : getToken
		}
	}

})();