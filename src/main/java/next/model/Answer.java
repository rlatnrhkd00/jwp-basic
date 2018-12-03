package next.model;

import java.util.Date;

public class Answer {
	private long questionId;
	private String writer;
	private String contents;
	private Date createDate;
	private long answerId;
	
	public Answer(String writer,String contents) {
		this(0,writer,contents,new Date(),0);
	}
	
	public Answer(long questionId,String writer,String contents,Date createDate,long answerId) {
		this.questionId = questionId;
		this.writer = writer;
		this.contents = contents;
		this.createDate = createDate;
		this.answerId = answerId;
	}
	
	public long getQuestionId(){
		return questionId;
	}
	

	public String getWriter() {
		return writer;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	public long getTimeFromCreatedDate() {
		return this.createDate.getTime();
	}
	public long getAnswerId() {
		return answerId;
	}

	@Override
	public int hashCode() {
		final int prime =31;
		int result = 1;
		
		result = prime*result+(int)(questionId^(questionId>>>32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(this.getClass()!=obj.getClass()) {
			return false;
		}
		Answer other = (Answer) obj;
		if(questionId != other.questionId) {
			return false;
		}else {
			return true;
		}
		
	}
	

	@Override
	public String toString() {
		return "Answer [answerId="+answerId+",writer ="+writer+","
				+ "contents="+contents+",createdDate"+createDate+",questionId="+questionId;
	}

	
	
}
