package board_handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 사용자가 요청을 했을때 맨 앞에서 어떤 명령을 호출할지 결정해준다.
public interface CommandHandler {

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
