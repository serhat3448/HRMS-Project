package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import hrms.hrms.business.abstracts.JobseekerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.Jobseeker;
import hrms.hrms.entities.dtos.CandidateForRegisterDto;
import hrms.hrms.entities.dtos.JobSeekerCVDto;


@RestController
@RequestMapping("/api/jobseekers")
@CrossOrigin
public class JobseekersController {

	private JobseekerService jobseekerService;

	@Autowired
	public JobseekersController(JobseekerService jobseekerService) {
		super();
		this.jobseekerService = jobseekerService;
	}
		
	//@PostMapping("/add")
	//public Result add(@RequestBody Jobseeker jobseeker){
	//	return this.jobseekerService.add(jobseeker);
	//}
	
	@PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CandidateForRegisterDto candidateForRegisterDto){
        Result result=this.jobseekerService.add(candidateForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
//	
//	@PostMapping("/update")
//	public Result update(@RequestBody Jobseeker jobseeker){
//		return this.jobseekerService.update(jobseeker);
//	}
//	
//	@PostMapping("/delete")
//	public Result delete(@RequestParam("id") int id){
//		return this.jobseekerService.delete(id);
//	}
//	
	@GetMapping("/getbyid")
	public DataResult<Jobseeker> getById(@RequestParam("id") int id){
		return this.jobseekerService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Jobseeker>> getAll(){
		return this.jobseekerService.getAll();
	}
	
	@GetMapping("/getJobseekerCVById")
	public DataResult<JobSeekerCVDto> getJobseekerCVById(@RequestParam int id){
		return this.jobseekerService.getJobseekerCVById(id);
	}

	
}