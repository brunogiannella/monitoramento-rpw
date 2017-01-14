var app = angular.module('app',['ui.router', 
							    'login.controller',
                  'home.controller',
                  'consultarOcorrenciasController.controller',
                  'cadastrarOcorrenciaController.controller',
                  'removerOcorrencia.controller',
                  'consultarTurnosUsuario.controller',
                  'iniciarNovoTurnoController.controller',
                  'finalizarTurno.controller',
                  'gerenciarCamerasController.controller',
                  'homeAdmin.controller',
                  'turnosPendentes.controller',
                  'aprovarTurno.controller',
                  'usuario.controller',
                  'removerUsuario.controller',
                  'editarUsuario.controller',
                  'cadastroUsuarioController.controller',
                  'cliente.controller',
                  'consultarTurnosCliente.controller',
                  'removerCliente.controller',
                  'editarCliente.controller',
                  'cadastroClienteController.controller',
                  'cadastrarCamera.controller',
                  'editarCamera.controller',
                  'removerCamera.controller',
                  'associarTipoOcorrencia.controller',
                  'associarTipoOcorrenciaPersonalizada.controller',
                  'tipoOcorrencia.controller',
                  'removerTipoOcorrencia.controller',
                  'cadastrarTipoOcorrenciaController.controller',
                  'desassociarTipoOcorrencia.controller',
                  'desassociarTipoOcorrenciaPersonalizada.controller',
                  'chat.controller',
                  'novoChat.controller',
                  'consultarChat.controller',
                  'removerChat.controller',
                  'imprimirRelatorio.controller',
                  'consultarRelatorioMensal.controller',

							    'constantes.service',
							    'utils.service',
                  'indicadores.service',
							    'login.service',
                  'turno.service',
                  'cliente.service',
                  'tipoOcorrencia.service',
                  'usuario.service',
                  'camera.service',
                  'chat.service',
                  'ocorrencia.service',
                  'relatorio.service',

                  'onFinishRender-diretiva']);
 
app.config(function($stateProvider, $urlRouterProvider, $locationProvider)
{
   	// remove o # da url
   	//$locationProvider.html5Mode(true);
 
   	$urlRouterProvider.otherwise('/');

  	$stateProvider
    .state('login', {
      url: '/',
      templateUrl: 'app/views/login.html',
      controller: 'LoginController as LoginCrtl'
    })
    .state('home-funcionario', {
      url: '/home',
      templateUrl: 'app/views/funcionario/home-funcionario.html',
      controller: 'HomeController as HomeCrtl'
    })
    .state('cadastrar-turno', {
      url: '/cadastrar-turno',
      templateUrl: 'app/views/funcionario/cadastrar-turno.html',
      controller: 'IniciarNovoTurnoController as IniciarNovoTurnoCrtl'
    })
    .state('consultar-turno', {
      url: '/consultar-turno',
      params: {
        idTurno : null,
        editar : null,
        consumidor : null
      },
      templateUrl: 'app/views/funcionario/consultar-turno.html',
      controller: 'ConsultarTurnosUsuarioController as ConsultarTurnosUsuarioCrtl'
    })
    .state('finalizar-turno', {
      url: '/finalizar-turno',
      params: {
        idTurno : null,
        dataInicio : null
      },
      templateUrl: 'app/views/funcionario/finalizar-turno.html',
      controller: 'FinalizarTurnoController as FinalizarTurnoCrtl'
    })
    .state('cadastrar-ocorrencia', {
      url: '/cadastrar-ocorrencia',
      params: {
        tiposOcorrencia : null,
        codigoTurno : null
      },
      templateUrl: 'app/views/funcionario/cadastrar-ocorrencia.html',
      controller: 'CadastrarOcorrenciaController as CadastrarOcorrenciaCrtl'
    })
    .state('remover-ocorrencia', {
      url: '/remover-ocorrencia',
      params: {
        idOcorrencia : null,
        idTurno : null
      },
      templateUrl: 'app/views/funcionario/remover-ocorrencia.html',
      controller: 'RemoverOcorrenciaController as RemoverOcorrenciaCrtl'
    })
    .state('gerenciar-cameras', {
      url: '/gerenciar-cameras',
      params: {
        idTurno : null
      },
      templateUrl: 'app/views/funcionario/gerenciar-cameras.html',
      controller: 'GerenciarCamerasController as GerenciarCamerasCrtl'
    })
    .state('consultar-ocorrencias-funcionario', {
      url: '/consultar-ocorrencias-funcionario',
      templateUrl: 'app/views/funcionario/consultar-ocorrencia.html',
      controller: 'ConsultarOcorrenciasController as ConsultarOcorrenciasCrtl'
    })
    .state('home-administrador', {
      url: '/home-admin',
      templateUrl: 'app/views/admin/home-admin.html',
      controller: 'HomeAdminController as HomeCrtl'
    })
    .state('turnos-pendentes', {
      url: '/turnos-pendentes',
      templateUrl: 'app/views/admin/turnos-pendentes.html',
      controller: 'TurnosPendentesController as TurnosPendentesCrtl'
    })
    .state('aprovar-turno', {
      url: '/aprovar-turno',
       params: {
        idTurno : null
      },
      templateUrl: 'app/views/admin/aprovar-turno.html',
      controller: 'AprovarTurnoController as AprovarTurnoCrtl'
    })
    .state('usuarios', {
      url: '/usuarios',
      templateUrl: 'app/views/admin/usuarios.html',
      controller: 'UsuarioController as UsuarioCrtl'
    })
    .state('remover-usuario', {
      url: '/remover-usuario',
      params: {
        idUsuario : null,
        loginUsuario : null
      },
      templateUrl: 'app/views/admin/remover-usuario.html',
      controller: 'RemoverUsuarioController as RemoverUsuarioCrtl'
    })
    .state('editar-usuario', {
      url: '/editar-usuario',
      params: {
        usuario : null
      },
      templateUrl: 'app/views/admin/cadastro-usuario.html',
      controller: 'EditarUsuarioController as CadastroUsuarioCrtl'
    })
    .state('cadastrar-usuario', {
      url: '/cadastrar-usuario',
      templateUrl: 'app/views/admin/cadastro-usuario.html',
      controller: 'CadastroUsuarioController as CadastroUsuarioCrtl'
    })
    .state('clientes', {
      url: '/clientes',
      params: {
        idCliente : null
      },
      templateUrl: 'app/views/admin/clientes.html',
      controller: 'ClienteController as ClienteCrtl'
    })
    .state('consultar-turnos-cliente', {
      url: '/consultar-turnos-cliente',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/consultar-turnos-cliente.html',
      controller: 'ConsultarTurnosClienteController as ConsultarTurnosClienteCrtl'
    })
    .state('remover-cliente', {
      url: '/remover-cliente',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/remover-cliente.html',
      controller: 'RemoverClienteController as RemoverClienteCrtl'
    })
    .state('editar-cliente', {
      url: '/editar-cliente',
      params: {
        cliente : null
      },
      templateUrl: 'app/views/admin/cadastro-cliente.html',
      controller: 'EditarClienteController as CadastroClienteCrtl'
    })
    .state('cadastrar-cliente', {
      url: '/cadastrar-cliente',
      templateUrl: 'app/views/admin/cadastro-cliente.html',
      controller: 'CadastroClienteController as CadastroClienteCrtl'
    })
    .state('cadastrar-camera', {
      url: '/cadastrar-camera',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/cadastrar-camera.html',
      controller: 'CadastrarCameraController as CadastrarCameraCrtl'
    })
    .state('remover-camera', {
      url: '/remover-camera',
      params: {
        idCliente : null,
        idCamera : null,
        nomeCamera : null
      },
      templateUrl: 'app/views/admin/remover-camera.html',
      controller: 'RemoverCameraController as RemoverCameraCrtl'
    })
    .state('editar-camera', {
      url: '/editar-camera',
      params: {
        camera : null
      },
      templateUrl: 'app/views/admin/cadastrar-camera.html',
      controller: 'EditarCameraController as CadastrarCameraCrtl'
    })
    .state('associar-tipo-ocorrencia', {
      url: '/associar-tipo-ocorrencia',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/associar-tipo-ocorrencia.html',
      controller: 'AssociarTipoOcorrenciaController as AssociarTipoOcorrenciaCrtl'
    })
    .state('associar-tipo-ocorrencia-personalizada', {
      url: '/associar-tipo-ocorrencia-personalizada',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/associar-tipo-ocorrencia.html',
      controller: 'AssociarTipoOcorrenciaPersonalizadaController as AssociarTipoOcorrenciaCrtl'
    })
    .state('desassociar-tipo-ocorrencia', {
      url: '/desassociar-tipo-ocorrencia',
      params: {
        idCliente : null,
        idTipoOcorrencia : null,
        nomeTipoOcorrencia : null
      },
      templateUrl: 'app/views/admin/desassociar-tipo-ocorrencia.html',
      controller: 'DesassociarTipoOcorrenciaController as DesassociarTipoOcorrenciaCrtl'
    })
    .state('desassociar-tipo-ocorrencia-personalizada', {
      url: '/desassociar-tipo-ocorrencia-personalizada',
      params: {
        idCliente : null,
        idTipoOcorrencia : null,
        nomeTipoOcorrencia : null
      },
      templateUrl: 'app/views/admin/desassociar-tipo-ocorrencia.html',
      controller: 'DesassociarTipoOcorrenciaPersonalizadaController as DesassociarTipoOcorrenciaCrtl'
    })
    .state('tipos-ocorrencia', {
      url: '/tipos-ocorrencia',
      templateUrl: 'app/views/admin/tipos-ocorrencia.html',
      controller: 'TipoOcorrenciaController as TipoOcorrenciaCrtl'
    })
    .state('remover-tipo-ocorrencia', {
      url: '/remover-tipo-ocorrencia',
      params: {
        idTipoOcorrencia : null,
        nomeTipoOcorrencia : null
      },
      templateUrl: 'app/views/admin/remover-tipo-ocorrencia.html',
      controller: 'RemoverTipoOcorrenciaController as RemoverTipoOcorrenciaCrtl'
    })
    .state('cadastrar-tipo-ocorrencias', {
      url: '/cadastrar-tipo-ocorrencias',
      templateUrl: 'app/views/admin/cadastrar-tipo-ocorrencias.html',
      controller: 'CadastrarTipoOcorrenciaController as CadastrarTipoOcorrenciaCrtl'
    })
    .state('chats', {
      url: '/chats',
      templateUrl: 'app/views/generico/chat.html',
      controller: 'ChatController as ChatCrtl'
    }).state('novo-chat', {
      url: '/novo-chat',
      templateUrl: 'app/views/generico/novo-chat.html',
      controller: 'NovoChatController as NovoChatCrtl'
    }).state('consultar-chat', {
      url: '/consultar-chat',
      params: {
        idChat : null
      },
      templateUrl: 'app/views/generico/consultar-chat.html',
      controller: 'ConsultarChatController as ConsultarChatCrtl'
    }).state('remover-chat', {
      url: '/remover-chat',
      params: {
        chat : null
      },
      templateUrl: 'app/views/generico/remover-chat.html',
      controller: 'RemoverChatController as RemoverChatCrtl'
    }).state('imprimir-relatorio', {
      url: '/imprimir-relatorio',
      params: {
        idTurno : null
      },
      templateUrl: 'app/views/admin/imprimir-relatorio.html',
      controller: 'ImprimirRelatorioController as ImprimirRelatorioCrtl'
    }).state('relatorio-mensal', {
      url: '/relatorio-mensal',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/relatorio-mensal.html',
      controller: 'ConsultarRelatorioMensalController as ConsultarRelatorioMensalCrtl'
    });


	    // Utilizando o HTML5 History API
	 $locationProvider.html5Mode(true);
});
