package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IUsuarioDao;
import br.com.rpw.monitoramento.api.model.Usuario;

@Component
public class UsuarioDaoImpl extends AbstractDao implements IUsuarioDao {
 
    public void salvarUsuario(Usuario usuario) {
    	usuario.setAtivo(true);
        persist(usuario);
    }
 
    @SuppressWarnings("unchecked")
    public List<Usuario> listarUsuarios() {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("ativo",true));
        return (List<Usuario>) criteria.list();
    }
 
    public void deleteEmployeeById(Long codigoUsuario) {
    	Usuario usuario = consultarUsuario(codigoUsuario);
    	usuario.setAtivo(false);
    	atualizarUsuario(usuario);
    }
 
     
    public Usuario autenticarUsuario(String usuario, String senha){
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("usuario",usuario));
        criteria.add(Restrictions.eq("senha",senha));
        return (Usuario) criteria.uniqueResult();
    }
     
    public void atualizarUsuario(Usuario usuario){
        getSession().update(usuario);
    }

	@Override
	public Usuario consultarUsuario(Long id) {
		 Criteria criteria = getSession().createCriteria(Usuario.class);
	     criteria.add(Restrictions.eq("id",id));
	     return (Usuario) criteria.uniqueResult();
	}
     
}
