package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.WorkPlace;
import hrms.hrms.entities.concretes.WorkTime;

public interface WorkTimeService {
	public DataResult<List<WorkTime>> getAll();
	Result add(WorkTime workTime);
}
