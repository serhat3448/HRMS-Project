package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.JobAdvertFavoriteService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvertFavorites;

//@RestController
@RequestMapping("/api/jobAdvertFavorites")
@CrossOrigin
public class JobAdvertFavoritesController {
	
	private JobAdvertFavoriteService jobAdvertFavoriteService;

	public JobAdvertFavoritesController(JobAdvertFavoriteService jobAdvertFavoriteService) {
		super();
		this.jobAdvertFavoriteService = jobAdvertFavoriteService;
	}
	
	@GetMapping("/getByJobseekerId")
    public ResponseEntity<?> getByJobseekerId(@RequestParam int jobseekerId){
        DataResult<List<JobAdvertFavorites>> result=this.jobAdvertFavoriteService.getByJobseekerId(jobseekerId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<?> addFavorite(@RequestParam int jobseekerId,@RequestParam int jobAdvertId){
        Result result=this.jobAdvertFavoriteService.addFavorite(jobseekerId,jobAdvertId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<?> removeFavorite(@RequestParam int favoriteId){
        Result result = this.jobAdvertFavoriteService.removeFavorite(favoriteId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

}
