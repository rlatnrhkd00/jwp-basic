package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class UpdateQuestionController implements Controller {
	QuestionDao questionDao = new QuestionDao();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		

        long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
      

        Question newQuestion = new Question( question.getWriter(),req.getParameter("title"), req.getParameter("contents"));
        question.update(newQuestion);
        questionDao.update(question,questionId);
        
        return "redirect:/";
	}

}
