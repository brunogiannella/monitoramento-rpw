(function() {

	'use strict';


	angular
		.module('usuario.service', [])
		.factory('UsuarioService', UsuarioService);

	UsuarioService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function UsuarioService($http, UtilsService, ConstantesService) {

		function cadastrarUsuario(usuarioDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario',
				method : 'POST',
				data: usuarioDto,
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

		function consultarUsuarios(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario',
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

		function consultarTiposUsuario(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario/tipoUsuario',
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
			cadastrarUsuario : cadastrarUsuario,
			consultarTiposUsuario : consultarTiposUsuario,
			consultarUsuarios : consultarUsuarios
		}
	}

})();