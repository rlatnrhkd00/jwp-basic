package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

public class DeleteQuestionController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }
		
		QuestionDao questionDao = new QuestionDao();
		

		long questionId = Long.parseLong(req.getParameter("questionId"));
		
        Question question = questionDao.findById(questionId);
        if (!question.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 삭제 할 수 없습니다.");
        }
		
		
		AnswerDao answerDao = new AnswerDao();
		List<Answer> answers = answerDao.findAll(questionId);
		for(Answer answer : answers) {
			answerDao.delete(answer.getAnswerId());
		}
		
		questionDao.delete(questionId);
		
		return "redirect:/";
	}

}
