package com.eventoesportivo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eventoesportivo.model.Evento;
import com.eventoesportivo.model.Convidado;
import com.eventoesportivo.repository.EventoRepository;
import com.eventoesportivo.repository.ConvidadoRepository;

@Controller
public class EventoController{

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository; 

	@RequestMapping(method = RequestMethod.GET, value = "/cadastroevento")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroevento");
		modelAndView.addObject("eventoobj", new Evento());
		Iterable<Evento> eventosIt = eventoRepository.findAll();
		modelAndView.addObject("eventos", eventosIt);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarevento")
	public ModelAndView salvar(@Valid Evento evento, BindingResult bindingResult) {
		
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroevento");
			Iterable<Evento> eventosIt = eventoRepository.findAll();
			modelAndView.addObject("eventos", eventosIt);
			modelAndView.addObject("eventoobj", evento);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		eventoRepository.save(evento);

		ModelAndView andView = new ModelAndView("cadastro/cadastroevento");
		Iterable<Evento> eventosIt = eventoRepository.findAll();
		andView.addObject("eventos", eventosIt);
		andView.addObject("eventoobj", new Evento());
			
		return andView;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/listaeventos")
	public ModelAndView eventos() {
		ModelAndView andView = new ModelAndView("cadastro/cadastroevento");
		Iterable<Evento> eventosIt = eventoRepository.findAll();
		andView.addObject("eventos", eventosIt);
		andView.addObject("eventoobj", new Evento());
		return andView;
	}
	
	
	@GetMapping("/editarevento/{idevento}")
	public ModelAndView editar(@PathVariable("idevento") Long idevento) {
		
		Optional<Evento> evento = eventoRepository.findById(idevento);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroevento");
		modelAndView.addObject("eventoobj", evento.get());
		return modelAndView;
		
	}
	
	
	@GetMapping("/removerevento/{idevento}")
	public ModelAndView excluir(@PathVariable("idevento") Long idevento) {
		
		eventoRepository.deleteById(idevento);	
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroevento");
		modelAndView.addObject("eventos", eventoRepository.findAll());
		modelAndView.addObject("eventoobj", new Evento());
		return modelAndView;
		
	}
	
	@PostMapping("**/pesquisarEvento")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroevento");
		modelAndView.addObject("eventos", eventoRepository.findEventoByName(nomepesquisa));
		modelAndView.addObject("eventoobj", new Evento());
		return modelAndView;
	}
	
	
	@GetMapping("/convidados/{idevento}")
	public ModelAndView eventos(@PathVariable("idevento") Long idevento) {
		
		Optional<Evento> evento = eventoRepository.findById(idevento);

		ModelAndView modelAndView = new ModelAndView("cadastro/convidados");
		modelAndView.addObject("eventoobj", evento.get());
		modelAndView.addObject("msg", new ArrayList<String>());
		modelAndView.addObject("convidados", convidadoRepository.getConvidados(idevento));
		return modelAndView;
		
	}
	
	@PostMapping("**/addconvidadoEvento/{eventoid}")
	public ModelAndView addConvEvento(Convidado convidado , 
									 @PathVariable("eventoid") Long eventoid) {
		
		Evento evento = eventoRepository.findById(eventoid).get();
		
		if(convidado != null && convidado.getNome().isEmpty() 
				|| convidado.getDocumento().isEmpty()) {
			
			ModelAndView modelAndView = new ModelAndView("cadastro/convidados");
			modelAndView.addObject("eventoobj", evento);
			modelAndView.addObject("convidados", convidadoRepository.getConvidados(eventoid));
			
			List<String> msg = new ArrayList<String>();
			if (convidado.getNome().isEmpty()) {
				msg.add("O Nome do convidado deve ser informado");
			}
			
			if (convidado.getDocumento().isEmpty()) {
				msg.add("Um documento deve ser informado");
			}
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
			
		}
		
		ModelAndView modelAndView = new ModelAndView("cadastro/convidados");

		convidado.setEvento(evento);
		
		convidadoRepository.save(convidado);
		
		modelAndView.addObject("eventoobj", evento);
		modelAndView.addObject("convidados", convidadoRepository.getConvidados(eventoid));
		return modelAndView;
	}
	
	@GetMapping("/removerconvidado/{idconvidado}")
	public ModelAndView removerconvidado(@PathVariable("idconvidado") Long idconvidado) {
		
		Evento evento = convidadoRepository.findById(idconvidado).get().getEvento();
		
		convidadoRepository.deleteById(idconvidado);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/eventos");
		modelAndView.addObject("eventoobj", evento);
		modelAndView.addObject("convidados", convidadoRepository.getConvidados(evento.getId()));
		return modelAndView;
		
	}
	
}
