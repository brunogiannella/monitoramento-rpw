var app = angular.module('app',['ui.router', 
							    'login.controller',
                  'home.controller',
                  'consultarOcorrenciasController.controller',
                  'cadastrarOcorrenciaController.controller',
                  'iniciarNovoTurnoController.controller',
                  'gerenciarCamerasController.controller',
                  'homeAdmin.controller',
                  'usuario.controller',
                  'removerUsuario.controller',
                  'editarUsuario.controller',
                  'cadastroUsuarioController.controller',
                  'cliente.controller',
                  'removerCliente.controller',
                  'editarCliente.controller',
                  'cadastroClienteController.controller',
                  'cadastrarCamera.controller',
                  'cadastrarEquipamento.controller',
                  'editarEquipamento.controller',
                  'removerEquipamento.controller',
                  'associarTipoOcorrencia.controller',
                  'tipoOcorrencia.controller',
                  'removerTipoOcorrencia.controller',
                  'cadastrarTipoOcorrenciaController.controller',

							    'constantes.service',
							    'utils.service',
							    'login.service',
                  'turno.service',
                  'cliente.service',
                  'tipoOcorrencia.service',
                  'usuario.service',
                  'camera.service',
                  'equipamento.service']);
 
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
    .state('cadastrar-ocorrencia', {
      url: '/cadastrar-ocorrencia',
      templateUrl: 'app/views/funcionario/cadastrar-ocorrencia.html',
      controller: 'CadastrarOcorrenciaController as CadastrarOcorrenciaCrtl'
    })
    .state('gerenciar-cameras', {
      url: '/gerenciar-cameras',
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
      templateUrl: 'app/views/admin/clientes.html',
      controller: 'ClienteController as ClienteCrtl'
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
    .state('cadastrar-equipamento', {
      url: '/cadastrar-equipamento',
      params: {
        idCliente : null,
        nomeCliente : null
      },
      templateUrl: 'app/views/admin/cadastrar-equipamento.html',
      controller: 'CadastrarEquipamentoController as CadastrarEquipamentoCrtl'
    })
    .state('editar-equipamento', {
      url: '/editar-equipamento',
      params: {
        equipamento : null
      },
      templateUrl: 'app/views/admin/cadastrar-equipamento.html',
      controller: 'EditarEquipamentoController as CadastrarEquipamentoCrtl'
    })
    .state('remover-equipamento', {
      url: '/remover-equipamento',
      params: {
        idEquipamento : null,
        nomeEquipamento : null
      },
      templateUrl: 'app/views/admin/remover-equipamento.html',
      controller: 'RemoverEquipamentoController as RemoverEquipamentoCrtl'
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
    });

	    // Utilizando o HTML5 History API
	 $locationProvider.html5Mode(true);
});
