(function() {

	'use strict';


	angular
		.module('chat.service', [])
		.factory('ChatService', ChatService);

	ChatService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function ChatService($http, UtilsService, ConstantesService) {

		function iniciarNovoChat(chatDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'chat',
				method : 'POST',
				data: chatDto,
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

		function listarChatsUsuario(idUsuario, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'chat/usuario/'+idUsuario,
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

		function enviarMensagem(mensagemDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'chat/mensagem',
				method : 'POST',
				data: mensagemDto,
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

		function consultarChat(idChat, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'chat/' + idChat,
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

		return {
			iniciarNovoChat : iniciarNovoChat,
			listarChatsUsuario : listarChatsUsuario,
			enviarMensagem : enviarMensagem,
			consultarChat : consultarChat
		}
	}

})();