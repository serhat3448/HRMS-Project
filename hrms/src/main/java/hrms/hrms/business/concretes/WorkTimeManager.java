package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.WorkTimeService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.dataAccess.abstracts.WorkTimeDao;
import hrms.hrms.entities.concretes.WorkTime;

@Service
public class WorkTimeManager implements WorkTimeService{

	private WorkTimeDao workTimeDao;

    @Autowired
    public WorkTimeManager(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }
	
	@Override
	public DataResult<List<WorkTime>> getAll() {
		return new SuccessDataResult<List<WorkTime>>(this.workTimeDao.findAll(),"Data listelendi");
	}

}
