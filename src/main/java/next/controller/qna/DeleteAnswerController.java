package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import next.model.User;

public class DeleteAnswerController implements Controller {
	private final static Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }
		
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();

		long answerId = Long.parseLong(req.getParameter("answerId"));
		long questionId = Long.parseLong(req.getParameter("questionId"));
		
		
		 Answer answer = answerDao.findById(answerId);
	        if (!answer.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
	            throw new IllegalStateException();
	        }
        
        
		
		
		
		
	
		
		if(answer == null)
			return null;
		
		
		answerDao.delete(answerId);
		questionDao.updatemCountOfAnswer(answer.getQuestionId());
		Question question = questionDao.findById(questionId); 
		
		ObjectMapper mapper = new ObjectMapper();
		Result result = new Result(true,"");
		result.setCountOfAnswer(question.getCountOfAnswer());
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(mapper.writeValueAsString(result));	
		return null;
	}


}
