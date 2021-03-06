package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.WorkPlaceService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.City;
import hrms.hrms.entities.concretes.WorkPlace;


@RestController
@RequestMapping("/workPlace")
@CrossOrigin
public class WorkPlacesController {
	
	private WorkPlaceService workPlaceService;

    @Autowired
    public WorkPlacesController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkPlace>> getAll(){
        return this.workPlaceService.getAll();
    }
    
	@PostMapping("/add")
	public Result add(@RequestBody WorkPlace workPlace){
		return this.workPlaceService.add(workPlace);
	}

}