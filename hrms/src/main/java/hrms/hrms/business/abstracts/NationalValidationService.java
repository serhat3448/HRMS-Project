package hrms.hrms.business.abstracts;

import hrms.hrms.entities.concretes.Jobseeker;

public interface NationalValidationService {
	boolean validate(Jobseeker jobseeker);
}
