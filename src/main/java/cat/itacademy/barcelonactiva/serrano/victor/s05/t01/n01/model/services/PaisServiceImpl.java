package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Pais;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.repository.PaisRepository;

@Service
public class PaisServiceImpl implements  IPaisService{
	
	@Autowired
	private PaisRepository paisRepository;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Pais> findAll() {
		
		return paisRepository.findAll();
	}

	@Override
	@Transactional
	public Pais save(Pais pais) {
	
		return paisRepository.save(pais);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		paisRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Pais> findById(int id) {
		
		return paisRepository.findById(id);
	}
	
}
