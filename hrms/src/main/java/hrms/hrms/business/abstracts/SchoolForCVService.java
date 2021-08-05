package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.SchoolForCV;
import hrms.hrms.entities.dtos.SchoolDto;

public interface SchoolForCVService {
	Result add(SchoolDto schoolDto);
	Result update(SchoolForCV schoolForCV);
	Result delete(int id);
	DataResult<SchoolForCV> getById(int id);	
	DataResult<List<SchoolForCV>> getAll();
	DataResult<List<SchoolForCV>> getAllByJobseekerIdOrderByEndAtDesc(int id);
	DataResult<List<SchoolForCV>> getAllByJobseekerId(int id);
}