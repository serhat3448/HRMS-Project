package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvert;
import hrms.hrms.entities.dtos.JobAdvertAddDto;

public interface JobAdvertService {
	//Result add(JobAdvertAddDto jobAdvertAddDto);
	Result create(JobAdvertAddDto jobAdvertAddDto);
	Result update(JobAdvert jobAdvert);
	Result delete(int id);
	Result changeOpenToClose(int id);
	DataResult<JobAdvert> getById(int id);	
	DataResult<List<JobAdvert>> getAll();
    DataResult<List<JobAdvert>> getAllByEmployerId(int id);
	DataResult<List<JobAdvert>> getAllOpenJobAdvertList();
	DataResult<List<JobAdvert>> findAllByOrderByPublishedAt();


}