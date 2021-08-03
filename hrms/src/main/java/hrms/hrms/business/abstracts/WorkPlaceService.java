package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.WorkPlace;
import hrms.hrms.entities.dtos.EmployerForRegisterDto;

public interface WorkPlaceService {
	
	public DataResult<List<WorkPlace>> getAll();
	Result add(WorkPlace workPlace);
	

}
