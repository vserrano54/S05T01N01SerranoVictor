package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.dto.SucursalDto;

public interface ISucursalService {
	
	public Iterable<SucursalDto> findAll();
	
	public SucursalDto save(SucursalDto sucursal);
	
	public SucursalDto update(SucursalDto sucursaldto, int id);
	
	public void deleteById(int id);
	
	public SucursalDto findById(int id);

}
