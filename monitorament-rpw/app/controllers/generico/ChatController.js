(function() {
	'use strict';

	angular
		.module('chat.controller', [])
		.controller('ChatController' , ChatController);

	ChatController.$inject = ['$rootScope', '$scope', '$stateParams', 'ChatService', 'UtilsService'];

	function ChatController($rootScope, $scope, $stateParams, ChatService, UtilsService) {

		var vm = this;
		vm.sair = UtilsService.logout;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.novoChat = novoChat;		

		function inicializar() {
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			UtilsService.ativarLoading();

			var funcSucesso = function(data) {
				if(data != null) {
					vm.chatsUsuario = data;
				}

				UtilsService.desativarLoading();
			};

			ChatService.listarChatsUsuario(codigoUsuario, funcSucesso);
		}

		function novoChat() {
			UtilsService.irPara('novo-chat');
		}

		function voltar() {
			if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
				UtilsService.irPara('home-administrador');
			} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
				UtilsService.irPara('home-supervisor');
			} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
				UtilsService.irPara('home-funcionario');
			}
		}

		function inicio() {
			voltar();
		}
		
		inicializar();

	}

})();