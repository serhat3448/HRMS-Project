package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.dtos.EmployerForRegisterDto;

public interface EmployerService {

	Result add(EmployerForRegisterDto employerForRegisterDto);
	//Result update(Employer employer);
	Result delete(int id);
	DataResult<Employer> getById(int id);
	
	DataResult<List<Employer>> getAll();
	DataResult<Employer> getByEmail(String email);

}