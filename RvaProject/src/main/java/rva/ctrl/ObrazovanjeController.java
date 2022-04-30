package rva.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Obrazovanje;
import rva.repository.ObrazovanjeRepository;

@RestController
public class ObrazovanjeController {
	
	@Autowired
	private ObrazovanjeRepository repositoryObrazovanje;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/obrazovanje")
	public Collection<Obrazovanje> getAllObrazovanje () {
		
		return repositoryObrazovanje.findAll();
		
	}
	
	@GetMapping("/obrazovanje/{id}") //path varijabla - id - URI RESURS
	public Obrazovanje getObrazovanjeById(@PathVariable int id ) {
		
		return repositoryObrazovanje.getById(id);
		
	}
	
	@GetMapping("/obrazovanje/naziv/{naziv}") //path varijabla - naziv
	public Collection<Obrazovanje> getObrazovanjeByName(@PathVariable String naziv ) {
		
		return repositoryObrazovanje.findByNazivContainingIgnoreCase(naziv);
		
	}
	
	//Repository sadrzi metode koje omogucavaju komunikaciju sa bazom
	@PostMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> createObrazovanje(@RequestBody Obrazovanje obrazovanje){
		if(repositoryObrazovanje.existsById(obrazovanje.getId())) {
			Obrazovanje temp = repositoryObrazovanje.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(temp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.CONFLICT);
		}
		
		
	}
	
	@PutMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> updateObrazovanje(@RequestBody Obrazovanje obrazovanje){
		if(repositoryObrazovanje.existsById(obrazovanje.getId())) {
			repositoryObrazovanje.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/obrazovanje/{id}")
	public ResponseEntity<Obrazovanje> deleteObrazovanje(@PathVariable int id){
		if(repositoryObrazovanje.existsById(id)) {
			repositoryObrazovanje.deleteById(id);
			jdbcTemplate.execute("INSERT INTO\"obrazovanje\"VALUES(-100,'Test naziv','Test opis')");
			return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
