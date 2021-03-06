(function() {
	'use strict';

	angular
		.module('cliente.controller', [])
		.controller('ClienteController' , ClienteController);

	ClienteController.$inject = ['$rootScope', '$scope', '$stateParams', 'ClienteService', 'UtilsService'];

	function ClienteController($rootScope, $scope, $stateParams, ClienteService, UtilsService) {

		var vm = this;
		vm.irPara = UtilsService.irPara;
		vm.cadastrar = cadastrar;
		vm.voltar = voltar;
		vm.consultarCliente = consultarCliente;
		vm.cadastrarCamera = cadastrarCamera;
		vm.associarOcorrencia = associarOcorrencia;
		vm.associarOcorrenciaPersonalizada = associarOcorrenciaPersonalizada;
		vm.removerCliente = removerCliente;
		vm.consultarTurnos = consultarTurnos;
		vm.editarCliente = editarCliente;
		vm.editarCamera = editarCamera;
		vm.removerCamera = removerCamera;
		vm.desassociarOcorrencia = desassociarOcorrencia;
		vm.desassociarOcorrenciaPersonalizada = desassociarOcorrenciaPersonalizada;
		vm.gerarRelatorioMensal = gerarRelatorioMensal;

		function inicializar() {
			$scope.sair = UtilsService.logout;
			vm.edicao = false;
			vm.idCliente = null;
			vm.cliente = null;
			$scope.clienteConsulta = $rootScope.dominios.clienteConsulta;

			if($stateParams.idCliente != null) {
				vm.edicao = true;

				var funcSucesso = function(data) {
					$scope.clientesListaConsulta = data;
				};

				ClienteService.consultarCliente($stateParams.idCliente, funcSucesso);
			}
		}

		function cadastrar() {
			UtilsService.irPara('cadastrar-cliente');
		}

		function voltar() {
			UtilsService.irPara('home-administrador');
		}

		function cadastrarCamera(id, nome) {
			UtilsService.irPara('cadastrar-camera', {idCliente:id, nomeCliente:nome});
		}

		function associarOcorrencia(id, nome) {
			UtilsService.irPara('associar-tipo-ocorrencia', {idCliente:id, nomeCliente:nome});
		}

		function associarOcorrenciaPersonalizada(id, nome) {
			UtilsService.irPara('associar-tipo-ocorrencia-personalizada', {idCliente:id, nomeCliente:nome});
		}

		function desassociarOcorrencia(id, idTipoOcorrencia, nome) {
			UtilsService.irPara('desassociar-tipo-ocorrencia', {idCliente:id, idTipoOcorrencia:idTipoOcorrencia, nomeTipoOcorrencia:nome});
		}

		function desassociarOcorrenciaPersonalizada(id, idTipoOcorrencia, nome) {
			UtilsService.irPara('desassociar-tipo-ocorrencia-personalizada', {idCliente:id, idTipoOcorrencia:idTipoOcorrencia, nomeTipoOcorrencia:nome});
		}

		function removerCliente(idCliente, nomeCliente) {
			UtilsService.irPara('remover-cliente', {idCliente:idCliente, nomeCliente:nomeCliente});
		}

		function consultarTurnos(idCliente, nomeCliente) {
			UtilsService.irPara('consultar-turnos-cliente', {idCliente:idCliente, nomeCliente:nomeCliente, tipoConsulta:'ultimos10', consumidor:'admin'});
		}

		function gerarRelatorioMensal(idCliente, nomeCliente) {
			UtilsService.irPara('relatorio-mensal', {idCliente:idCliente, nomeCliente:nomeCliente, consumidor:'admin'});
		}

		function editarCliente(data) {
			var clienteEdicao = {};
			clienteEdicao.id = data.id;
			clienteEdicao.nome = data.nome;
			clienteEdicao.emailResposavel = data.emailResposavel;
			clienteEdicao.emailAutomatico = data.emailAutomatico;

			clienteEdicao.endereco = {};
			if(data.endereco != null) {
				clienteEdicao.endereco.id = data.endereco.id;
				clienteEdicao.endereco.logradouro = data.endereco.logradouro;
				clienteEdicao.endereco.bairro = data.endereco.bairro;
				clienteEdicao.endereco.cidade = data.endereco.cidade;
				clienteEdicao.endereco.estado = data.endereco.estado;
				clienteEdicao.endereco.cep = data.endereco.cep;
			}

			clienteEdicao.emailsRelatorioDiario = data.emailsRelatorioDiario;
			clienteEdicao.emailsRelatorioMensal = data.emailsRelatorioMensal;
			
			UtilsService.irPara('editar-cliente', {cliente:clienteEdicao});
		}

		function editarCamera(data) {
			var cameraEdicao = {};
			cameraEdicao.id = data.id;
			cameraEdicao.numeroCamera = data.numeroCamera;
			cameraEdicao.descricaoCamera = data.descricaoCamera;
			cameraEdicao.localizacaoCamera = data.localizacaoCamera;
			cameraEdicao.idCliente = data.idCliente;
			cameraEdicao.tipoCamera = data.tipoCamera;
			UtilsService.irPara('editar-camera', {camera:cameraEdicao});
		}

		function removerCamera(idCliente, idCamera, nomeCamera) {
			UtilsService.irPara('remover-camera', {idCliente:idCliente, idCamera:idCamera, nomeCamera:nomeCamera});
		}

		function consultarCliente() {
			vm.edicao = true;

			var funcSucesso = function(data) {
				$scope.clientesListaConsulta = data;
			};

			ClienteService.consultarCliente(vm.idCliente, funcSucesso);
		}

		inicializar();

	}

})();