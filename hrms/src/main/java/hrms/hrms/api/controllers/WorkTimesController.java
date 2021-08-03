package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.WorkTimeService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.WorkPlace;
import hrms.hrms.entities.concretes.WorkTime;


@RestController
@RequestMapping("/workTime")
@CrossOrigin
public class WorkTimesController {

    private WorkTimeService workTimeService;

    @Autowired
    public WorkTimesController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkTime>> getAll(){
        return this.workTimeService.getAll();
    }
    
	@PostMapping("/add")
	public Result add(@RequestBody WorkTime workTime){
		return this.workTimeService.add(workTime);
	}
}
