package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class CreateQuestionController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(!UserSessionUtils.isLogined(req.getSession()));
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }
		
		
		Question question = new Question(req.getParameter("writer"),req.getParameter("title"),req.getParameter("contents"));
		
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(question);
		
		return "redirect:/";
	}

}
