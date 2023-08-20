package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Pais;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.dto.SucursalDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services.IPaisService;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services.ISucursalService;

@Controller
//@Slf4j
@RequestMapping("/views/sucursal")
public class SucursalWebController {
	//private static final Logger logger = LoggerFactory.getLogger(SucursalWebController.class);
	
	@Autowired
	private ISucursalService iSucursalService;
	@Autowired
	private IPaisService iPaisService;
	@Autowired
	private MessageSource messageSource;
	
	//Crear un registro de Sucursal
	
	@GetMapping("/add")
	public String create (Model model, Locale locale){
		
		SucursalDto  sucursal = new SucursalDto();
		
		List<Pais> paises = (List<Pais>) iPaisService.findAll();
		
		String titulo = messageSource.getMessage("sucursal.nuevo.titulo", null, locale);
		
		model.addAttribute("titulo", titulo);
		model.addAttribute("sucursal",sucursal);
		model.addAttribute("paises",paises);
		return "/views/sucursal/crear";
	
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute SucursalDto sucursal, Errors errores, 
				Model model,RedirectAttributes attribute, Locale locale ) {
		
		System.out.println("Entra al procedimiento save\n\n\n\n");
	
		
		List<Pais> paises = (List<Pais>) iPaisService.findAll();
		if (errores.hasErrors()) {
			System.out.println("Entra al hasssErrors\n\n\n\n");
			String titulo = messageSource.getMessage("sucursal.nuevo.titulo", null, locale);
			model.addAttribute("titulo", titulo);
			model.addAttribute("sucursal",sucursal);
			model.addAttribute("paises",paises);
			//logger.info("existieron errores en el formulario");
			//System.out.println("existieron errores en el formulario");
			
			return "/views/sucursal/crear";
		}
		
		System.out.println("No encontro errores\n\n\n\n");
		
		iSucursalService.save(sucursal);
		//System.out.println("Sucursal guardada con exito");
		String success = messageSource.getMessage("sucursal.accion.guardar.exito", null, locale);
		attribute.addFlashAttribute("success", success);
		return "redirect:/views/sucursal/getAll";
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/update/{id}")
	public String update (@PathVariable("id") int id, Model model, RedirectAttributes attribute, Locale locale){
		
		SucursalDto sucursal=null;
		
		if (id>0)
		{
			sucursal = iSucursalService.findById(id);
			
			if (sucursal.equals(null)||(sucursal.equals(Optional.empty()))) {
				System.out.println("El id de la sucursal no existe");
				String error = messageSource.getMessage("sucursal.accion.guardar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/sucursal/getAll";
			}
		}else {
			System.out.println("Error con el id de la sucursal");
			String error = messageSource.getMessage("sucursal.accion.guardar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/sucursal/getAll";
		}
		List<Pais> paises = (List<Pais>) iPaisService.findAll();
		
		String titulo = messageSource.getMessage("sucursal.editar.titulo", null, locale);
		model.addAttribute("titulo", titulo);
		model.addAttribute("sucursal",sucursal);
		model.addAttribute("paises",paises);
		return "/views/sucursal/crear";
	
	}
	
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable("id") int id, RedirectAttributes attribute, Locale locale){
		
		SucursalDto sucursal=null;
		
		if (id>0)
		{
			sucursal = iSucursalService.findById(id);
			
			if (sucursal.equals(null)||(sucursal.equals(Optional.empty()))) {
				String error = messageSource.getMessage("sucursal.accion.eliminar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/sucursal/getAll";
			}
		}else {
			System.out.println("Error con el id de la sucursal");
			String error = messageSource.getMessage("sucursal.accion.eliminar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/sucursal/getAll";
		}
		
		iSucursalService.deleteById(id);
		
		System.out.println("Registro eliminado con exito");
		String warning = messageSource.getMessage("sucursal.accion.eliminar.exito", null, locale);
		attribute.addFlashAttribute("warning", warning);
		
		return "redirect:/views/sucursal/getAll";
	
	}
	
	@GetMapping("/getAll")
	public String readAll(Model model, Locale locale) {
		
		List<SucursalDto> sucursales = (List<SucursalDto>) iSucursalService.findAll();
		
		
		String titulo = messageSource.getMessage("sucursal.lista.titulo", null, locale);
		model.addAttribute("sucursales",sucursales);
		model.addAttribute("titulo",titulo);
		return "/views/sucursal/listar";
		
	}
	
}