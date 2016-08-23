(function() {

	'use strict';


	angular
		.module('camera.service', [])
		.factory('CameraService', CameraService);

	CameraService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function CameraService($http, UtilsService, ConstantesService) {

		function inserirAvaliacaoCamera(situacaoCameraDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'situacaocamera',
				method : 'POST',
				data: situacaoCameraDTO,
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

		function listarTiposCamera(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'camera/tipoCamera',
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

		function consultarCamera(idCamera, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'camera/' + idCamera,
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

		function listarCamerasCliente(idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'camera/cliente/' + idCliente,
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
			inserirAvaliacaoCamera : inserirAvaliacaoCamera,
			listarTiposCamera : listarTiposCamera,
			consultarCamera : consultarCamera,
			listarCamerasCliente : listarCamerasCliente
		}
	}

})();