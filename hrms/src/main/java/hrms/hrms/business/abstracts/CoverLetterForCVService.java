package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.CoverLetterForCV;

public interface CoverLetterForCVService {

	Result add(CoverLetterForCV coverLetterForCV);
	Result update(CoverLetterForCV coverLetterForCV);
	Result delete(int id);
	DataResult<CoverLetterForCV> getById(int id);	
	DataResult<List<CoverLetterForCV>> getAll();
}