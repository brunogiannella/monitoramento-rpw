(function() {

	'use strict';


	angular
		.module('indicadores.service', [])
		.factory('IndicadoresService', IndicadoresService);

	IndicadoresService.$inject = ['$http', 'ConstantesService'];

	function IndicadoresService($http, ConstantesService) {


		function consultarQuantidadeClientes(fncSucesso) {
			$http.get(
				ConstantesService.URL.SERVIDOR + 'indicador/quantidadeClientes').then(
			       function(response){
			       		if(response.data.sucess) {
				       	  	fncSucesso(response.data.data);
				       	} else {
				       	  	return false;
				       	}
			       }, 
			       function(response){
			       }
			    )
			  	.finally(function() {
				})
				.catch(function() {
				});
		};

		function consultarQuantidadeUsuarios(fncSucesso) {
			$http.get(
				ConstantesService.URL.SERVIDOR + 'indicador/quantidadeUsuarios').then(
			       function(response){
			       		if(response.data.sucess) {
				       	  	fncSucesso(response.data.data);
				       	} else {
				       	  	return false;
				       	}
			       }, 
			       function(response){
			       }
			    )
			  	.finally(function() {
				})
				.catch(function() {
				});
		};

		function consultarQuantidadeTiposOcorrencias(fncSucesso) {
			$http.get(
				ConstantesService.URL.SERVIDOR + 'indicador/quantidadeTiposOcorrencia').then(
			       function(response){
			       		if(response.data.sucess) {
				       	  	fncSucesso(response.data.data);
				       	} else {
				       	  	return false;
				       	}
			       }, 
			       function(response){
			       }
			    )
			  	.finally(function() {
				})
				.catch(function() {
				});
		};

		function consultarQuantidadeTiposOcorrenciaPersonalizadas(fncSucesso) {
			$http.get(
				ConstantesService.URL.SERVIDOR + 'indicador/quantidadeTiposOcorrenciaPersonalizadas').then(
			       function(response){
			       		if(response.data.sucess) {
				       	  	fncSucesso(response.data.data);
				       	} else {
				       	  	return false;
				       	}
			       }, 
			       function(response){
			       }
			    )
			  	.finally(function() {
				})
				.catch(function() {
				});
		};

		return {
			consultarQuantidadeClientes : consultarQuantidadeClientes,
			consultarQuantidadeUsuarios : consultarQuantidadeUsuarios,
			consultarQuantidadeTiposOcorrencias : consultarQuantidadeTiposOcorrencias,
			consultarQuantidadeTiposOcorrenciaPersonalizadas : consultarQuantidadeTiposOcorrenciaPersonalizadas
		}
	}

})();