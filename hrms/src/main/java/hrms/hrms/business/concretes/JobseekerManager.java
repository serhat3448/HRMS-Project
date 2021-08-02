package hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmailService;
import hrms.hrms.business.abstracts.JobseekerService;
import hrms.hrms.business.abstracts.NationalValidationService;
import hrms.hrms.business.abstracts.UserService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.JobseekerDao;
import hrms.hrms.entities.concretes.Jobseeker;
import hrms.hrms.entities.dtos.CandidateForRegisterDto;
import hrms.hrms.entities.dtos.JobSeekerCVDto;
import net.bytebuddy.asm.Advice.This;

@Service
public class JobseekerManager implements JobseekerService {

	private JobseekerDao jobseekerDao;
	private UserService userService;
	private NationalValidationService nationalValidationService;
	private EmailService emailService;

	public JobseekerManager(JobseekerDao jobseekerDao) {
		super();
		this.jobseekerDao = jobseekerDao;

	}

	@Override
	public Result add(CandidateForRegisterDto candidateDto) {
		 if(!candidateDto.getPassword().equals(candidateDto.getRePassword())){
	            return new ErrorResult("Şifreler eşleşmiyor");
	        }
		 
		 	Jobseeker jobseeker=new Jobseeker();
		 	jobseeker.setFirstName(candidateDto.getFirstName());
		 	jobseeker.setLastName(candidateDto.getLastName());
		 	jobseeker.setNationalId(candidateDto.getNationalId());
		 	jobseeker.setDateOfBirth(candidateDto.getBirthDate());
		 	jobseeker.setEmail(candidateDto.getEmail());
		 	jobseeker.setPassword(candidateDto.getPassword());

		 
		 	if(jobseeker.getPassword().length() <=6){
	            return new ErrorResult("Şifre 6 karakterden uzun olmalıdır");
	        }else if(!isEmailValid(jobseeker.getEmail())){
	            return new ErrorResult("Email geçerli formatta değil");
	        }else if(getByNationalId(jobseeker.getNationalId()).getData() != null){
	            return new ErrorResult("Bu kimlik numarası zaten kayıtlı");
	        }else if(getByEmail(jobseeker.getEmail()).getData() != null){
	            return new ErrorResult("Bu email zaten kayıtlı");
	        }else if(1==1){
	        	//jobseeker.setMailVerify(false);
	            this.jobseekerDao.save(jobseeker);
	            //this.emailService.sendVerifyEmail(jobseeker,this.activationCodeService.createActivationCode(jobseeker));
	            return new SuccessResult(jobseeker.getEmail()+" Adresine doğrulama kodu gönderildi");
	        }else{
	            return new ErrorResult("Kullanıcı kimlik bilgileri hatalı");
	        }
	}

	@Override
	public Result update(Jobseeker jobseeker) {
		this.jobseekerDao.save(jobseeker);
		return new SuccessResult("Jobseeker has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.jobseekerDao.deleteById(id);
		return new SuccessResult("Jobseeker has been deleted.");
	}

	@Override
	public DataResult<Jobseeker> getById(int id) {
		return new SuccessDataResult<Jobseeker>(this.jobseekerDao.getById(id));
	}

	@Override
	public DataResult<List<Jobseeker>> getAll() {
		return new SuccessDataResult<List<Jobseeker>>(this.jobseekerDao.findAll());
	}

	@Override
	public DataResult<Jobseeker> getByNationalId(String nationalId) {
		return new SuccessDataResult<Jobseeker>(this.jobseekerDao.findJobseekerByNationalId(nationalId));
	}

	@Override
	public DataResult<JobSeekerCVDto> getJobseekerCVById(int id) {
		Jobseeker jobseeker = this.jobseekerDao.getById(id);
		JobSeekerCVDto cv = new JobSeekerCVDto();
		cv.experiences = jobseeker.getExperiences();
		cv.languages = jobseeker.getLanguages();
		cv.image = jobseeker.getImage();
		cv.links = jobseeker.getLinks();
		cv.programingSkills = jobseeker.getProgramingSkills();
		//cv.schools = jobseeker.getSchools();
		//cv.jobseeker = jobseeker;
		return new SuccessDataResult<JobSeekerCVDto>(cv);
	}
	
	private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
	
	public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }

	@Override
	public DataResult<Jobseeker> getByEmail(String email) {
		return new SuccessDataResult<Jobseeker>(this.jobseekerDao.findByEmail(email),"Listelendi");
	}



	

}