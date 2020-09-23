package board_handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullCommandHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		resp.sendError(resp.SC_NOT_FOUND); // 404
		
		return null;
	}

	
}
