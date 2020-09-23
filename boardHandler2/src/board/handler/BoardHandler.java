package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardHandler {
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
