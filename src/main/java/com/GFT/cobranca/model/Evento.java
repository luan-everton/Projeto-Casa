package com.GFT.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	public Casa casaShows;

	
	 private String Genero;
	 
	 
	 private String lotacao;
	 
	 @DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	 private Date dataEvento;
	 
	 @NumberFormat(pattern="#,##0.00")
	 private BigDecimal valor;
	 
	 
	 public Casa getCasaShows() {
			return casaShows;
		}

		public void setCasaShows(Casa casaShows) {
			this.casaShows = casaShows;
		}

		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}


	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date  dataEvento) {
		this.dataEvento = dataEvento;
	}
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (codigo == null) {
			if (other.codigo!= null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	 
	
}
