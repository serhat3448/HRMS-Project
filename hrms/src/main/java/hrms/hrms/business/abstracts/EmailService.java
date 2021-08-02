package hrms.hrms.business.abstracts;

import hrms.hrms.entities.concretes.User;

public interface EmailService {
	void sendVerifyEmail(User user,String code);
}
