package hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmployerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.EmployerDao;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.dtos.EmployerForRegisterDto;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		this.employerDao = employerDao;
	}

	@Override
	public Result add(EmployerForRegisterDto employerDto) {
		if(!employerDto.getPassword().equals(employerDto.getRePassword())){
            return new ErrorResult("Şifreler eşleşmiyor");
        }
        Employer employer=new Employer();
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebsite(employerDto.getWebSite());
        employer.setPhoneNumber(employerDto.getPhoneNumber());
        //employer.setWaitingUpdate(false);
        //Burada personel onay kısmı yapılacak.


       //if(EmployerService.getByEmail(employer.getEmail()).getData() != null){
       if(!isEmailValid(employer.getEmail())){
           return new ErrorResult("Geçerli bir email giriniz");
       }else if(!employer.getEmail().endsWith(employer.getWebsite())){
           return new ErrorResult("Web siteniz ve emailinizin domaini aynı olmalıdır");
       }else if(employer.getPassword().length() <=6 ){
           return new ErrorResult("Şifre 6 karakterden uzun olmalıdır.");
       }else if(employer.getPhoneNumber().length() <10){
           return new ErrorResult("Geçerli bir telefon numarası giriniz.");
       }else if(employer.getCompanyName().length()<=2){
           return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
       }

       //employer.setActive(false);

       this.employerDao.save(employer);




       return new SuccessResult(employer.getEmail()+" Adresine doğrulama kodunuz gönderildi");
	}
//
//	@Override
//	public void update(Employer employer) {
//		this.employerDao.save(employer);
//      return new SuccessResult("Employer has been updated.");
//	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
	    return new SuccessResult("Employer has been deleted.");
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(this.employerDao.getById(id));
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email),"Listelendi");
		
	}
	
	private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

	
	public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }



}