package br.com.rpw.monitoramento.api.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ISituacaoCameraDao;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.model.Turno;

@Component
public class SituacaoCameraDaoImpl extends AbstractDao implements ISituacaoCameraDao {

	@Override
	public void salvarSituacaoCamera(SituacaoCamera situacaoCamera) {
		persist(situacaoCamera);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> listarSituacaoCameras(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<SituacaoCamera>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> listarSituacaoCamerasEmAberto(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("desligada", true));
		criteria.add(Restrictions.eq("ligada", false));
        return (List<SituacaoCamera>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> listarSituacaoCamerasMensal(Cliente cliente, String mes, String ano) throws ParseException {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    String myDate = "01/"+mes+"/"+ano;
	    Date minDate = formatter.parse(myDate);
	    Date maxDate = null;
	    
	    if(mes.equals("12")) {
	    	String myDateEnd = "01/01/"+String.valueOf((Integer.parseInt(ano) + 1));
		    maxDate = formatter.parse(myDateEnd);
	    } else {
	    	
	    	String mesSeguinte = "";
	    	
	    	if(Integer.parseInt(mes) >= 10) {
	    		mesSeguinte = String.valueOf((Integer.parseInt(mes) + 1));
	    	} else {
	    		mesSeguinte = "0" + String.valueOf((Integer.parseInt(mes) + 1));
	    	}
	    	
	    	String myDateEnd = "01/"+mesSeguinte+"/"+ano;
		    maxDate = formatter.parse(myDateEnd);
	    }
		
	    Conjunction and = Restrictions.conjunction();
	    and.add( Restrictions.ge("dataHoraDesligada", minDate) );
	    and.add( Restrictions.lt("dataHoraDesligada", maxDate) ); 
	    
	    criteria.add(and);
		
        return (List<SituacaoCamera>) criteria.list();
	}

	@Override
	public void deleteSituacaoCamera(Long codigoSituacaoCamera) {
		Query query = getSession().createSQLQuery("delete from SITUACAO_CAMERA where id = :id");
        query.setLong("id", codigoSituacaoCamera);
        query.executeUpdate();
	}

	@Override
	public SituacaoCamera consultarSituacaoCamera(Long idSituacaoCamera) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
        criteria.add(Restrictions.eq("id",idSituacaoCamera));
        return (SituacaoCamera) criteria.uniqueResult();
	}
	
	@Override
	public Integer consultarSituacaoCamera(Cliente cliente, Camera camera) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("camera.id",camera.getId()));
		criteria.add(Restrictions.eq("desligada", true));
		criteria.add(Restrictions.eq("ligada", false));
        return criteria.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> consultarSituacaoCameraTurno(Turno turno) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("turno.id",turno.getId()));
        return criteria.list();
	}

	@Override
	public void atualizarSituacaoCamera(SituacaoCamera situacaoCamera) {
		getSession().update(situacaoCamera);
	}

}
