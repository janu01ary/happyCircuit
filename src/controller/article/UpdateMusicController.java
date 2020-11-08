package controller.article;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Music;
import model.MusicArticle;
import model.dao.MusicDAO;

public class UpdateMusicController implements Controller {

	private MusicDAO musicDAO = new MusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// �ۼ��ڿ� �α��� ���̵� �˻� �߰�
		
		// �˻��ؼ� ���� �� ä���!
		if (request.getMethod().equals("GET")) {
			int musicId = Integer.parseInt(request.getParameter("id"));
			MusicArticle musicBoard = musicDAO.findMusicArticle(musicId);
			request.setAttribute("musicboard", musicBoard);

			return "/article/articleModify.jsp";
		}
		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		int originalMusicId = Integer.parseInt(request.getParameter("originalMusicId"));
		int priorMusicId = Integer.parseInt(request.getParameter("priorMusicId"));
		int nth = 0;
		//String nickname = request.getParameter("nickname");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId"); 
		File file = null; // �޾ƿ� ����
		String musicPath = null;
		
		String content = request.getParameter("content");
		int readCount =  Integer.parseInt(request.getParameter("readCount"));
		int likeCount = Integer.parseInt(request.getParameter("likecount"));
		
		Music music = new Music(originalMusicId, priorMusicId, userId, title, genre, nth, musicPath);
		MusicArticle musicArticle = new MusicArticle(music, content, readCount, likeCount);
		musicDAO.updateMusicArticle(musicArticle);
		
		return "redirect:/article/articleMain.jsp";
	}

}