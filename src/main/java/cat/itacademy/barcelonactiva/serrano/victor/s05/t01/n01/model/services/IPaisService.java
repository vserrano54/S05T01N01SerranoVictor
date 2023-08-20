package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services;

import java.util.Optional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Pais;

public interface IPaisService {
	
	public Iterable<Pais> findAll();
	
	public Pais save(Pais pais);
	
	public void deleteById(int id);
	
	public Optional<Pais> findById(int id);

}
