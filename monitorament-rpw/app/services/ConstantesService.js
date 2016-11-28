(function() {
	
	'use strict';

	angular
		.module('constantes.service', [])
		.factory('ConstantesService', ConstantesService);

	ConstantesService.$inject = [];


	function ConstantesService() {


		this.URL = {
			//SERVIDOR : 'http://localhost:8080/poc-bigdata/',
			SERVIDOR : 'http://centerrpw.ddns.com.br:8080/monitoramento-rpw-service/',
		}

		return {
			URL : this.URL
		};
	}

})();