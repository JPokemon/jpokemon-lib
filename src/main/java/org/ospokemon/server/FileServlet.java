package org.ospokemon.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Serves up files and lets you establish a websocket connection
 * 
 * @author zach
 * 
 */
public class FileServlet extends HttpServlet {
	// By default serve the server accessories
	public static String WEBDIR_DEFAULT = "src/www";
	public static String cache = "no-store,no-cache,must-revalidate";

	protected String webdir;

	protected ResourceHandler resourceHandler;

	public FileServlet() {
		this(WEBDIR_DEFAULT);
	}

	public FileServlet(String webdir) {
		this.webdir = webdir;
		resourceHandler = new ResourceHandler();
		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setCacheControl(cache);
		resourceHandler.setResourceBase(webdir);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resourceHandler.handle(req.getRequestURI(), (Request) req, req, resp);

		// if (!resp.isCommitted()) {
		// resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		// resp.setContentType("text/html");
		// resp.getWriter().println(
		// "<h1>Resource Not Found</h1><br/>Sorry, the resource you've requested could not be located.");
		// }
	}

	private static final long serialVersionUID = 1L;
}