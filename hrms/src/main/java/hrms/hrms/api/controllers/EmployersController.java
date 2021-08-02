package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.EmployerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.dtos.CandidateForRegisterDto;
import hrms.hrms.entities.dtos.EmployerForRegisterDto;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin
public class EmployersController {
	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	
	@PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerForRegisterDto employerForRegisterDto){
        Result result=this.employerService.add(employerForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
//	
//	@PostMapping("/update")
//	public Result update(@RequestBody Employer employer){
//		return this.employerService.update(employer);
//	}
	
	@PostMapping("/delete")
	public Result delete(@RequestParam("id") int id){
		return this.employerService.delete(id);
	}
	
	@GetMapping("/getbyid")
	public DataResult<Employer> getById(@RequestParam("id") int id){
		return this.employerService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
	}
}