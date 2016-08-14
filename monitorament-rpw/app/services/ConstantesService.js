(function() {
	
	'use strict';

	angular
		.module('constantes.service', [])
		.factory('ConstantesService', ConstantesService);

	ConstantesService.$inject = [];


	function ConstantesService() {


		this.URL = {
			SERVIDOR : 'http://localhost:8080/poc-bigdata/',
		}

		return {
			URL : this.URL
		};
	}

})();