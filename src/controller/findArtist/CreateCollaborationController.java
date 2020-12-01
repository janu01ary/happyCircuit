package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Collaboration;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class CreateCollaborationController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	CollaborationDAO collaborationDAO = new CollaborationDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/findArtist/list";	
        }
		
		// GET method: �ʱⰪ ���� �� form ȭ�� ���
		if (request.getMethod().equals("GET")) {
			System.out.println("(CreateCollaborationController) IN ");
			int postId = Integer.parseInt(request.getParameter("postId"));
			System.out.println("(CreateCollaborationController) postId: " + postId);
			
			try {
				Post post = postDAO.findPost(postId);
				request.setAttribute("post", post);	
			} catch (Exception e) {
				return "redirect:/findArtist/view/post?postId=" + postId;
			}
			
			return "/findArtist/createCollaboration.jsp";
		}
		
		// POST method: �ۼ��� �����͸� �޾ƿ� DB ����
		// httpsession�� �޾ƿͼ� collaborationArtistId(������û�� ����� id) �޾ƿ�
		HttpSession session = request.getSession();
		String collaborationArtistId = ArtistSessionUtils.getLoginArtistId(session);
		System.out.println("(CreateCollaborationController) collaborationArtistId: " + collaborationArtistId);
		
		try {
			int postId = Integer.parseInt(request.getParameter("postId"));
			Post post = postDAO.findPost(postId);
			String postArtistId = post.getArtistId();
			
			String collaborationTitle = request.getParameter("collaborationTitle");
			String collaborationContent = request.getParameter("collaborationContent");
			
			Collaboration collaboration = new Collaboration(
					0, collaborationTitle,
					null, collaborationContent,
					postId, postArtistId, collaborationArtistId);
			System.out.println("(CreateCollaborationController) collaboration ��ü ���� �Ϸ� ");
			
			int collaborationId = collaborationDAO.create(collaboration);
			request.setAttribute("collaborationId", collaborationId);
			
			return "redirect:/findArtist/view/collaboration?collaborationId=" + collaborationId;
		} catch (Exception e) {
			return "/findArtist/createCollaboration.jsp";
		}
		
	}

}