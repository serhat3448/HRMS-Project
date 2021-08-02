package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.User;
import hrms.hrms.entities.dtos.UserLoginDto;
import hrms.hrms.entities.dtos.UserLoginReturnDto;

public interface UserService {

	Result add(User user);
//	Result update(User user);
//	Result delete(int id);
//	DataResult<User> getById(int id);
	
	DataResult<List<User>> getAll();
	DataResult<User> getByEmail(String email);
	DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto);
}