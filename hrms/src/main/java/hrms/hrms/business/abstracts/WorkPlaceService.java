package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.entities.concretes.WorkPlace;

public interface WorkPlaceService {
	
	public DataResult<List<WorkPlace>> getAll();

}
