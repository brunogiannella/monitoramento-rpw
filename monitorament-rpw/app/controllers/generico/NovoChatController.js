(function() {
	'use strict';

	angular
		.module('novoChat.controller', [])
		.controller('NovoChatController' , NovoChatController);

	NovoChatController.$inject = ['$rootScope', '$scope', '$stateParams', 'ChatService', 'UtilsService'];

	function NovoChatController($rootScope, $scope, $stateParams, ChatService, UtilsService) {

		var vm = this;
		vm.sair = UtilsService.logout;
		vm.voltar = voltar;
		vm.inicio = inicio;	
		vm.abrirChat = abrirChat;	

		function inicializar() {
			vm.chatDto = {};
			vm.chatDto.codigoUsuarioFrom = $rootScope.usuarioLogado.idUsuario;
			vm.chatDto.nomeUsuarioFrom = $rootScope.usuarioLogado.nomeUsuario;
			vm.listaUsuariosTo = $rootScope.dominios.toMensagens;
		}

		function abrirChat() {
			var funcSucesso = function(data) {
				alert("Chat iniciado com sucesso");
				UtilsService.irPara('chats');
			};

			ChatService.iniciarNovoChat(vm.chatDto, funcSucesso);
		};

		function voltar() {
			UtilsService.irPara('chats');
		};

		function inicio() {
			if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
				UtilsService.irPara('home-administrador');
			} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
				UtilsService.irPara('home-supervisor');
			} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
				UtilsService.irPara('home-funcionario');
			}
		};
		
		inicializar();

	}

})();