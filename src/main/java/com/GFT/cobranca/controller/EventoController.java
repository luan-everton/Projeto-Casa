package com.GFT.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	List<Evento>listaEventos= eventos.findAll();
	mv.addObject("eventos",listaEventos);
	mv.addObject(new Evento());
    return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView CadastrarEventos(Evento evento , RedirectAttributes attributes) {
	ModelAndView mv = new ModelAndView("redirect:/novo");
	List<Casa>listaCasas= casas.findAll();
	mv.addObject("casas",listaCasas);
	List<Evento>listaEventos= eventos.findAll();
	mv.addObject("eventos",listaEventos);
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
	List<Casa>listaCasas= casas.findAll();
	mv.addObject("casas",listaCasas);
    return mv;
	}
	
	
	@PostMapping("/CadastroEvento")
	public ModelAndView CadastroPost( Evento evento , RedirectAttributes attributes, Errors errors) {
		
		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView(CADASTRO_VIEW);

			List<Evento>listaevento= eventos.findAll();
			mv.addObject(new Evento());
			mv.addObject("eventos",listaevento);
			return mv;
		}
			eventos.save(evento);
			attributes.addFlashAttribute("mensagem", " Casa cadastrada  com sucesso ");
			ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
			List<Evento>listaevento= eventos.findAll();
			mv.addObject(new Evento());
			mv.addObject("eventos",listaevento);
			
			return  mv ;	
		}
	
	
	@RequestMapping("/novo/{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo ) {
		Evento evento = eventos.findById(codigo).get();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Evento> todoseventos = eventos.findAll();
		List<Casa>listaCasas= casas.findAll();
		mv.addObject("casas",listaCasas);
		mv.addObject("todoseventos", todoseventos);
		mv.addObject(evento);
		return mv;
		
	}
	
	@RequestMapping("/novo/{codigo")
	public String excluir(@PathVariable Long codigo) {
		eventos.deleteById(codigo);
		return "redirect:/pesquisaShow";
	}
	
}
