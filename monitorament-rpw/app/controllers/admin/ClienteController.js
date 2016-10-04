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
		vm.cadastrarEquipamento = cadastrarEquipamento;
		vm.associarOcorrencia = associarOcorrencia;
		vm.removerCliente = removerCliente;
		vm.editarCliente = editarCliente;
		vm.editarEquipamento = editarEquipamento;
		vm.removerEquipamento = removerEquipamento;

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

		function cadastrarEquipamento(id, nome) {
			UtilsService.irPara('cadastrar-equipamento', {idCliente:id, nomeCliente:nome});
		}

		function associarOcorrencia(id, nome) {
			UtilsService.irPara('associar-tipo-ocorrencia', {idCliente:id, nomeCliente:nome});
		}

		function removerCliente(idCliente, nomeCliente) {
			UtilsService.irPara('remover-cliente', {idCliente:idCliente, nomeCliente:nomeCliente});
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

		function editarEquipamento(data) {
			var equipamentoEdicao = {};
			equipamentoEdicao.id = data.id;
			equipamentoEdicao.numero = data.numero;
			equipamentoEdicao.descricao = data.descricao;
			equipamentoEdicao.localizacao = data.localizacao;
			equipamentoEdicao.idCliente = data.idCliente;
			equipamentoEdicao.tipoEquipamento = data.tipoEquipamento;
			UtilsService.irPara('editar-equipamento', {equipamento:equipamentoEdicao});
		}

		function removerEquipamento(idEquipamento, nomeEquipamento) {
			UtilsService.irPara('remover-equipamento', {idEquipamento:idEquipamento, nomeEquipamento:nomeEquipamento});
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