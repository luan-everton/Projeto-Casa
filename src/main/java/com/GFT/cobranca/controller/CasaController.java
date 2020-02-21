package com.GFT.cobranca.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.GFT.cobranca.model.Casa;
import com.GFT.cobranca.model.Evento;
import com.GFT.cobranca.repository.CasaRepositorio;



@Controller
public class CasaController {
	private static final String CASA_VIEW = "CadastroCasa";
	private static final String CASA_HOME = "home";
  ;
	
	
	@Autowired
	private CasaRepositorio casas;
	
	
	
	
	
	@GetMapping("/Cadastro")
	public ModelAndView listaCadastroCasa() {
		ModelAndView mv = new ModelAndView(CASA_VIEW);
		List<Casa>listaCasas= casas.findAll();
		mv.addObject(new Casa());
		mv.addObject("casas",listaCasas);

		return  mv;
		
	}
	
	@PostMapping("/Cadastro")
	public ModelAndView CadastroPost( @Validated Casa casa , RedirectAttributes attributes, Errors errors) {
		
		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView(CASA_VIEW);

			List<Casa>listaCasas= casas.findAll();
			mv.addObject(new Casa());
			mv.addObject("casas",listaCasas);
			return mv;
		}
			casas.save(casa);
			attributes.addFlashAttribute("mensagem", " Casa cadastrada  com sucesso ");
			ModelAndView mv = new ModelAndView(CASA_VIEW);
			List<Casa>listaCasas= casas.findAll();
			mv.addObject(new Casa());
			mv.addObject("casas",listaCasas);
			
			return  mv ;	
		}
	
	
	@RequestMapping({"/home","/"})
	public ModelAndView home() {
	ModelAndView mv = new ModelAndView(CASA_HOME);
	mv.addObject(new Casa());
    return mv;
	}
	
	
	@RequestMapping("/Cadastro/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Casa casa) {   
	ModelAndView mv = new ModelAndView(CASA_VIEW);
	mv.addObject(new Casa());
    return mv;
	}
	@GetMapping(value ="/Cadastro/delete/{codigo}")
	public ModelAndView excluir(@PathVariable Long codigo) {
		ModelAndView mv = new ModelAndView("redirect:/Cadastro");
		casas.deleteById(codigo);
		return mv;
	}
	
	
	
	
	
	
	}
	

	



