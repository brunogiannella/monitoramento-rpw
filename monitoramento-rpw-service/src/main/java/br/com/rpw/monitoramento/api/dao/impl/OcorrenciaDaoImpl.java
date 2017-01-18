package br.com.rpw.monitoramento.api.dao.impl;

import java.math.BigInteger;
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
import br.com.rpw.monitoramento.api.dao.IOcorrenciaDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Turno;

@Component
public class OcorrenciaDaoImpl extends AbstractDao implements IOcorrenciaDao {

	@Override
	public void salvarOcorrencia(Ocorrencia ocorrencia) {
		persist(ocorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> listarOcorrencias(Turno turno) {
		Criteria criteria = getSession().createCriteria(Ocorrencia.class);
		criteria.add(Restrictions.eq("turno.id",turno.getId()));
        return (List<Ocorrencia>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> listarOcorrencias(Cliente cliente, String mes, String ano) throws ParseException {
		Criteria criteria = getSession().createCriteria(Ocorrencia.class);
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
	    and.add( Restrictions.ge("dataCadastro", minDate) );
	    and.add( Restrictions.lt("dataCadastro", maxDate) ); 
	    
	    criteria.add(and);
		
        return (List<Ocorrencia>) criteria.list();
	}

	@Override
	public void deleteOcorrenciaById(Long codigoOcorrencia) {
		Query query = getSession().createSQLQuery("delete from OCORRENCIA where id = :id");
        query.setLong("id", codigoOcorrencia);
        query.executeUpdate();
	}

	@Override
	public Ocorrencia consultarOcorrencia(Ocorrencia ocorrencia) {
		Criteria criteria = getSession().createCriteria(Ocorrencia.class);
        criteria.add(Restrictions.eq("id",ocorrencia.getId()));
        return (Ocorrencia) criteria.uniqueResult();
	}

	@Override
	public BigInteger consultarQuantidadeOcorrenciasClienteData(Cliente cliente, Integer mes, Integer ano) {
		Query query = getSession().createSQLQuery("SELECT count(*) from OCORRENCIA where ID_CLIENTE = " + cliente.getId() + " AND MONTH(DATA_CADASTRO) = " + mes + " AND YEAR(DATA_CADASTRO) = " + ano + ";");
        BigInteger quantidadeClientes = (BigInteger) query.uniqueResult();
        
        return quantidadeClientes;
	}
	
	@Override
	public void atualizarOcorrencia(Ocorrencia ocorrencia) {
		getSession().update(ocorrencia);
	}
 
   
     
}
