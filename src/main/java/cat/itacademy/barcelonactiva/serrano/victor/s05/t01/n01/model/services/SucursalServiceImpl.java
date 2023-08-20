package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.excepciones.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.dto.SucursalDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.repository.SucursalRepository;

@Service
public class SucursalServiceImpl implements ISucursalService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	@Transactional(readOnly=true)
	public List<SucursalDto> findAll() {

		List<Sucursal> sucursales = sucursalRepository.findAll();
		
		return sucursales.stream().map(sucursal -> mapearDTO(sucursal)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public SucursalDto save(SucursalDto sucursalDto) {
		
		Sucursal sucursal = mapearEntidad(sucursalDto);
		
		Sucursal nuevaSucursal = sucursalRepository.save(sucursal);
		
		SucursalDto sucursaldto = mapearDTO(nuevaSucursal);
		
		return sucursaldto;
	}
	
	@Override
	@Transactional
	public SucursalDto update(SucursalDto sucursalDto, int id) {

		Sucursal sucursal = sucursalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sucursal", "id", id));

		sucursal.setNombre(sucursalDto.getNombre());
		sucursal.setPais(sucursalDto.getPais());

		Sucursal sucursalActualizada = sucursalRepository.save(sucursal);
		
		Sucursal buscarSucursalActualizada=sucursalRepository.findById(sucursalActualizada.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Sucursal", "id", id));

		return mapearDTO(buscarSucursalActualizada);

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		Sucursal sucursal = sucursalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sucursal", "id", id));
		
		sucursalRepository.delete(sucursal);
		
	}

	@Override
	@Transactional(readOnly=true)
	public SucursalDto findById(int id) {
		
	    Sucursal sucursal = sucursalRepository.findById(id)
	    		.orElseThrow(() -> new ResourceNotFoundException("Sucursal", "id", id));
	    
	    return mapearDTO(sucursal);
	   
	}
	
	private SucursalDto mapearDTO(Sucursal sucursal) {
		/*SucursalDto sucursaldto = new SucursalDto();
		sucursaldto.setId(sucursal.getId());
		sucursaldto.setNombre(sucursal.getNombre());
		sucursaldto.setPais(sucursal.getPais());
		*/
		SucursalDto sucursaldto= modelMapper.map(sucursal, SucursalDto.class);
		if (sucursal.getPais().getContinente().getNombre().equals("Europa")) 
			sucursaldto.setTipoSucursal("UE");
		else 
			sucursaldto.setTipoSucursal("Fuera UE");
		
		return sucursaldto;
	}
	
	private Sucursal mapearEntidad(SucursalDto sucursaldto) {
		Sucursal sucursal= modelMapper.map(sucursaldto, Sucursal.class);
		/*
		Sucursal sucursal = new Sucursal();
		sucursal.setId(sucursaldto.getId());
		sucursal.setNombre(sucursaldto.getNombre());
		sucursal.setPais(sucursaldto.getPais());
		*/
		return sucursal;
	}
	

}
