package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ICameraDao;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;

@Component
public class CameraDaoImpl extends AbstractDao implements ICameraDao{

	@Override
	public void salvarCamera(Camera camera) {
		camera.setAtivo(true);
		persist(camera);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Camera> listarCameras(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Camera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("ativo", true));
        return (List<Camera>) criteria.list();
	}
	
	@Override
	public void deleteCamerasByCliente(Long codigoCliente) {
		Query query = getSession().createSQLQuery("delete from CAMERA where ID_CLIENTE = :id");
        query.setLong("id", codigoCliente);
        query.executeUpdate();
	}

	@Override
	public void deleteCamera(Long codigoCamera) {
		Camera camera = consultarCamera(codigoCamera);
		camera.setAtivo(false);
		atualizarCamera(camera);
	}

	@Override
	public Camera consultarCamera(Long idCamera) {
		Criteria criteria = getSession().createCriteria(Camera.class);
        criteria.add(Restrictions.eq("id",idCamera));
        return (Camera) criteria.uniqueResult();
	}

	@Override
	public void atualizarCamera(Camera Camera) {
		getSession().update(Camera);
	}

}
