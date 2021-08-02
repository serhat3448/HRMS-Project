package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.WorkTimeService;
import hrms.hrms.core.utilities.results.DataResult;
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
}
