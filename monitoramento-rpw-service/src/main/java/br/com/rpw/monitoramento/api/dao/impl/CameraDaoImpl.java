package br.com.rpw.monitoramento.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Camera> listarCameras(Cliente cliente, TipoCameraEnum tipoCamera) {
		Criteria criteria = getSession().createCriteria(Camera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("ativo", true));
		criteria.add(Restrictions.eq("tipoCamera", tipoCamera));

        return (List<Camera>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Camera> listarCamerasPorClienteENumero(Cliente cliente, String numeroCamera) {
		Criteria criteria = getSession().createCriteria(Camera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("numeroCamera", numeroCamera));
		criteria.add(Restrictions.eq("ativo", true));

        return (List<Camera>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarCamerasGroupNumeroCamera(Cliente cliente) {
		Query query = getSession().createSQLQuery("SELECT NUMERO_CAMERA FROM CAMERA WHERE ID_CLIENTE = :idCliente AND (TIPO_CAMERA = 'FIXA' OR TIPO_CAMERA = 'DOME') GROUP BY NUMERO_CAMERA");
        query.setLong("idCliente", cliente.getId());

        List<String> retorno = query.list();
        
        List<String> numerosCameras = new ArrayList<String>();
        for(String row : retorno){
        	numerosCameras.add(row);
        }

        
		return numerosCameras;
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
