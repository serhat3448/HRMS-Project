package hrms.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.SchoolForCVService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.JobseekerDao;
import hrms.hrms.dataAccess.abstracts.SchoolForCVDao;
import hrms.hrms.entities.concretes.JobAdvert;
import hrms.hrms.entities.concretes.SchoolForCV;
import hrms.hrms.entities.dtos.SchoolDto;

@Service
public class SchoolForCVManager implements SchoolForCVService{

	private SchoolForCVDao schoolForCVDao;
	private JobseekerDao jobseekerDao;
		
	@Autowired
	public SchoolForCVManager(SchoolForCVDao schoolForCVDao, JobseekerDao jobseekerDao) {
		super();
		this.schoolForCVDao = schoolForCVDao;
		this.jobseekerDao = jobseekerDao;
	}

	@Override
	public Result add(SchoolDto schoolDto) {
		SchoolForCV schoolForCV=new SchoolForCV();
        schoolForCV.setSchoolName(schoolDto.getSchoolName());
        schoolForCV.setDepartment(schoolDto.getDepartment());
        schoolForCV.setStartAt(schoolDto.getStartAt());
        schoolForCV.setEndAt(schoolDto.getEndAt());
        schoolForCV.setJobseeker(this.jobseekerDao.getById(schoolDto.getJobseeker_id()));

        this.schoolForCVDao.save(schoolForCV);

		return new SuccessResult("School has been added.");
	}

	@Override
	public Result update(SchoolForCV schoolForCV) {
		this.schoolForCVDao.save(schoolForCV);
		return new SuccessResult("School has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.schoolForCVDao.deleteById(id);
		return new SuccessResult("School has been deleted.");
	}

	@Override
	public DataResult<SchoolForCV> getById(int id) {
		return new SuccessDataResult<SchoolForCV>(this.schoolForCVDao.getById(id));
	}

	@Override
	public DataResult<List<SchoolForCV>> getAll() {
		return new SuccessDataResult<List<SchoolForCV>>(this.schoolForCVDao.findAll());
	}

	@Override
	public DataResult<List<SchoolForCV>> getAllByJobseekerIdOrderByEndAtDesc(int id) {
		return new SuccessDataResult<List<SchoolForCV>>(this.schoolForCVDao.getAllByJobseeker_idOrderByEndAtDesc(id));
	}

	@Override
	public DataResult<List<SchoolForCV>> getAllByJobseekerId(int id) {
		return new SuccessDataResult<List<SchoolForCV>>(this.schoolForCVDao.getAllByJobseeker_id(id));
	}

}