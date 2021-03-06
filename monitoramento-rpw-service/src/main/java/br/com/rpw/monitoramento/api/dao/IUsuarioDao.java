package br.com.rpw.monitoramento.api.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.rpw.monitoramento.api.constantes.TipoUsuarioEnum;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface IUsuarioDao {

    void salvarUsuario(Usuario employee);
    List<Usuario> listarUsuarios();
    void deleteEmployeeById(Long codigoUsuario);
    Usuario autenticarUsuario(String usuario, String senha);
    void atualizarUsuario(Usuario employee);
    Usuario consultarUsuario(Long id);
	BigInteger consultarQuantidadeUsuariosAtivos();
	List<Usuario> listarUsuarios(TipoUsuarioEnum tipoUsuario);
	
}
