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

import hrms.hrms.business.abstracts.SchoolForCVService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result; 
import hrms.hrms.entities.concretes.SchoolForCV;
import hrms.hrms.entities.dtos.EmployerForRegisterDto;
import hrms.hrms.entities.dtos.SchoolDto;


@RestController
@RequestMapping("/api/schools")
@CrossOrigin
public class SchoolsController {

	private SchoolForCVService schoolForCVService;

	@Autowired
	public SchoolsController(SchoolForCVService schoolForCVService) {
		super();
		this.schoolForCVService = schoolForCVService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody SchoolDto schoolDto){
		return this.schoolForCVService.add(schoolDto);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody SchoolForCV schoolForCV){
		return this.schoolForCVService.update(schoolForCV);
	}
	
	/*
	 *
	 *	@PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerForRegisterDto employerForRegisterDto){
        Result result=this.employerService.add(employerForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    
    *
    *	@PostMapping("/delete")
	public Result delete(@RequestParam int id){
		return this.schoolForCVService.delete(id);
	}
    */
	
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int id){
		Result result = this.schoolForCVService.delete(id);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("/getbyid")
	public DataResult<SchoolForCV> getById(@RequestParam int id){
		return this.schoolForCVService.getById(id);
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<SchoolForCV>> getAll(){
		return this.schoolForCVService.getAll();
	}
	
	@GetMapping("/getAllByJobseekerIdOrderByEndAtDesc")
	public DataResult<List<SchoolForCV>> getAllByJobseekerIdOrderByEndAtDesc(@RequestParam int id){
		return this.schoolForCVService.getAllByJobseekerIdOrderByEndAtDesc(id);
	}
	
	@GetMapping("/getAllByJobseekerId")
	public DataResult<List<SchoolForCV>> getAllByJobseekerId(@RequestParam int id){
		return this.schoolForCVService.getAllByJobseekerId(id);
	}
}