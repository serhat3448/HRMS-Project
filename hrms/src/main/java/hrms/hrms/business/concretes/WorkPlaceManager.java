package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.WorkPlaceService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.WorkPlaceDao;
import hrms.hrms.entities.concretes.WorkPlace;


@Service
public class WorkPlaceManager implements WorkPlaceService{

	private WorkPlaceDao workPlaceDao;
	
	@Autowired
    public WorkPlaceManager(WorkPlaceDao workPlaceDao) {
        this.workPlaceDao = workPlaceDao;
    }

	
	@Override
	public DataResult<List<WorkPlace>> getAll() {
		return new SuccessDataResult<List<WorkPlace>>(this.workPlaceDao.findAll(),"Data listelendi");
	}


	@Override
	public Result add(WorkPlace workPlace) {
		this.workPlaceDao.save(workPlace);
		return new SuccessResult("Workplace has been added.");
	}

}
