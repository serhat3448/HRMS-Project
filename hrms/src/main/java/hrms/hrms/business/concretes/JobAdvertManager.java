package hrms.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobAdvertService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.CityDao;
import hrms.hrms.dataAccess.abstracts.EmployerDao;
import hrms.hrms.dataAccess.abstracts.JobAdvertDao;
import hrms.hrms.dataAccess.abstracts.JobPositionDao;
import hrms.hrms.dataAccess.abstracts.WorkPlaceDao;
import hrms.hrms.dataAccess.abstracts.WorkTimeDao;
import hrms.hrms.entities.concretes.JobAdvert;
import hrms.hrms.entities.dtos.JobAdvertAddDto;
import net.bytebuddy.asm.Advice.This;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;
	private JobPositionDao jobPositionDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	private WorkPlaceDao workPlaceDao;
	private WorkTimeDao workTimeDao;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao, JobPositionDao jobPositionDao, EmployerDao employerDao, CityDao cityDao, WorkTimeDao workTimeDao, WorkPlaceDao workPlaceDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
		this.jobPositionDao = jobPositionDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.workPlaceDao = workPlaceDao;
		this.workTimeDao = workTimeDao;
	}

	//@Override
	//public Result add(JobAdvertAddDto jobAdvertAddDto) {
	//	if (!CheckIfNullField(jobAdvertAddDto)) {
	//		return new ErrorResult("You have entered missing information. Please fill in all fields.");
	//	}
	//	this.jobAdvertDao.saveAll(jobAdvertAddDto);
	//	return new SuccessResult("Job advert has been added.");
	//}

	@Override
	public Result update(JobAdvert jobAdvert) {
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("Job advert has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.jobAdvertDao.deleteById(id);
		return new SuccessResult("Job advert has been deleted.");
	}

	@Override
	public DataResult<JobAdvert> getById(int id) {
		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.findById(id));
	}

	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll());
	}


	private boolean CheckIfNullField(JobAdvert jobAdvert) {
		if (jobAdvert.getJobPosition() != null && jobAdvert.getDescription() != null && jobAdvert.getCity() != null
				&& jobAdvert.getOpenPositionCount() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Result changeOpenToClose(int id) {
		if (getById(id) == null) {
			return new ErrorResult("There is no such job advert");

		}
		if (getById(id).getData().isOpen() == false) {
			return new ErrorResult("There job advert is already closed.");
		}

		JobAdvert jobAdvert = getById(id).getData();
		jobAdvert.setOpen(false);
		update(jobAdvert);
		return new SuccessResult("Job advert has been successfully closed.");
	}

	@Override
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertList() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertList());
	}

	@Override
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedAt() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByOrderByPublishedAtDesc());
	}



	@Override
	public Result create(JobAdvertAddDto jobAdvertAddDto) {


        JobAdvert jobAdvert=new JobAdvert();
        jobAdvert.setId(0);
        jobAdvert.setPublishedAt(LocalDate.now());
        jobAdvert.setDeadline(jobAdvertAddDto.getDeadline());
        jobAdvert.setDescription(jobAdvertAddDto.getDescription());
        jobAdvert.setSalaryMin(jobAdvertAddDto.getSalaryMin());
        jobAdvert.setSalaryMax(jobAdvertAddDto.getSalaryMax());
        jobAdvert.setOpenPositionCount(jobAdvertAddDto.getOpenPositionCount());
        //jobAdvert.setActive(false);
        jobAdvert.setCity(this.cityDao.getById(jobAdvertAddDto.getCityId()));
        jobAdvert.setEmployer(this.employerDao.getById(jobAdvertAddDto.getEmployerId()));
        jobAdvert.setJobPosition(this.jobPositionDao.getById(jobAdvertAddDto.getJobPositionId()));
        jobAdvert.setWorkTime(this.workTimeDao.getById(jobAdvertAddDto.getWorkTimeId()));
        jobAdvert.setWorkPlace(this.workPlaceDao.getById(jobAdvertAddDto.getWorkPlaceId()));

 

        
       
        //jobAdvert.setConfirmed(false);
        this.jobAdvertDao.save(jobAdvert);

        /*JobAdActivation jobAdActivation=new JobAdActivation();
        jobAdActivation.setJobAdId(jobAd.getId());
        jobAdActivation.setConfirm(false);
        this.jobAdActivationDao.save(jobAdActivation);
		*/

        return new SuccessResult("İlan başarılı bir şekilde eklendi");
	}


	@Override
	public DataResult<List<JobAdvert>> getAllByEmployerId(int id) {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByEmployerId(id),"Şirkere göre aktif iş işanları listelendi");

	}


}