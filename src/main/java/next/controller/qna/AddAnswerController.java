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
import next.model.Answer;
import next.model.Question;

public class AddAnswerController implements Controller {
	private final static Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			throw new IllegalStateException();
        }
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		QuestionDao questionDao = new QuestionDao();
		
		Answer answer = new Answer(req.getParameter("writer"),req.getParameter("contents"),questionId);
	
		AnswerDao answerDao = new AnswerDao();
		
		Answer savedAnswer = answerDao.insert(answer);
		questionDao.updatepCountOfAnswer(savedAnswer.getQuestionId());
		
        Question question = questionDao.findById(questionId);
		savedAnswer.setCountOfAnswer(question.getCountOfAnswer());
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(mapper.writeValueAsString(savedAnswer));	
		

		return null;
	}

}
