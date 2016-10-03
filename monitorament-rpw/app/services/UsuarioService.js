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
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		function atualizarUsuario(usuarioDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario',
				method : 'PUT',
				data: usuarioDto,
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
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
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
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
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		function removerUsuario(idUsuario, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario/'+idUsuario,
				method : 'DELETE',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		function consultarUsuario(idUsuario, fncSucesso, controller) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'usuario/'+idUsuario,
				method : 'GET',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data, controller);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		return {
			cadastrarUsuario : cadastrarUsuario,
			atualizarUsuario : atualizarUsuario,
			consultarTiposUsuario : consultarTiposUsuario,
			consultarUsuarios : consultarUsuarios,
			consultarUsuario : consultarUsuario,
			removerUsuario : removerUsuario
		}
	}

})();