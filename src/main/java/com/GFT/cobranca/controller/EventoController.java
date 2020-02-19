package com.GFT.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.GFT.cobranca.model.Casa;
import com.GFT.cobranca.model.Evento;
import com.GFT.cobranca.repository.CasaRepositorio;
import com.GFT.cobranca.repository.EventoRepositorio;

@Controller
public class EventoController {
	private static final String CADASTRO_VIEW = "CadastroEvento";
	  private static final String CASA_PESQUISA = "pesquisaShow";
	  

	
	@Autowired
	  private EventoRepositorio eventos;

	@Autowired
	private CasaRepositorio casas;
	
	
	@GetMapping("/novo")
	public ModelAndView novo() {
	ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
	List<Casa>listaCasas= casas.findAll();
	mv.addObject("casas",listaCasas);

	mv.addObject(new Evento());
    return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView CadastrarEventos(Evento evento , RedirectAttributes attributes) {
	ModelAndView mv = new ModelAndView("redirect:/novo");
	eventos.save(evento);
	
	mv.addObject(new Evento());
	
	attributes.addFlashAttribute("mensagem", " Evento  cadastrado com sucesso ");
	
    return mv;
	}
	
	@GetMapping("/pesquisa")
	public ModelAndView listaEvento() {
	ModelAndView mv = new ModelAndView(CASA_PESQUISA);
	List<Evento>listaEventos= eventos.findAll();
	mv.addObject(new Evento());
	mv.addObject("eventos",listaEventos);
    return mv;
	}
	
	
	
}
