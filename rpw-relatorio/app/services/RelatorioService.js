(function() {

	'use strict';


	angular
		.module('relatorio.service', [])
		.factory('RelatorioService', RelatorioService);

	RelatorioService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function RelatorioService($http, UtilsService, ConstantesService) {

		
		function consultarRelatorioMensal(mes, ano, idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'relatorios/'+idCliente+'/mensal/'+ano+'/'+mes,
				method : 'GET',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		         return false;
		       }
		    ).catch(function() {
				return false;
			});
		};

		function consultarRelatorioDiario(idTurno, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'relatorios/diario/turno/'+idTurno,
				method : 'GET',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		         return false;
		       }
		    ).catch(function() {
				return false;
			});
		};

		return {
			consultarRelatorioMensal : consultarRelatorioMensal,
			consultarRelatorioDiario : consultarRelatorioDiario
		}
	}

})();