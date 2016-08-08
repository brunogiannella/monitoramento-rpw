package br.com.rpw.monitoramento.api.model.pk;

import java.io.Serializable;

public class TelefonePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1705617177890980939L;
	
	private Integer ddd;
	private Integer telefone;

	
	public TelefonePK() {
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	@Override
    public boolean equals(Object obj) {
        if(obj instanceof TelefonePK){
        	TelefonePK telPk = (TelefonePK) obj;
 
            if(!telPk.getDdd().equals(ddd)){
                return false;
            }
 
            if(!telPk.getTelefone().equals(telefone)){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
}
