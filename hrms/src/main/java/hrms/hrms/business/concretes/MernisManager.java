package hrms.hrms.business.concretes;

import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.NationalValidationService;
import hrms.hrms.entities.concretes.Jobseeker;

@Service
public class MernisManager implements NationalValidationService{

	@Override
	public boolean validate(Jobseeker jobseeker) {
		 if(jobseeker.getNationalId().length()!=11){
	            return false;
	        }
	        return true;
	}
}
