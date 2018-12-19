package next.controller.qna;

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

public class UpdateFormAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }
		
		 AnswerDao answerDao = new AnswerDao();

		long answerId = Long.parseLong(req.getParameter("answerId"));
        Answer answer = answerDao.findById(answerId);
        if (!answer.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 수정 할 수 없습니다.");
        }
		
		

       
		
        
        req.setAttribute("answer", answer);
        
        return "/qna/form3.jsp";
	}

}
