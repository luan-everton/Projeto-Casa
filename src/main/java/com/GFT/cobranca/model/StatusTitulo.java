package com.GFT.cobranca.model;

public enum StatusTitulo {
	CD("Morumbi - SP"),
	CC("Alians Arena - SP");
	
	
 private String conta;
 
  StatusTitulo(String conta){
	  this.conta = conta;
  }
  
  public String getConta() {
	
	  return conta;
  }

    
}
