package controller.artist;

import java.awt.Image;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.Artist;
import model.dao.ArtistDAO;

public class UpdateArtistController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form 요청으로 redirect
        }
    	
		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		
		//GET request: form 요청
		if (request.getMethod().equals("GET")) {
			request.setAttribute("artist", artist);
			
			if (ArtistSessionUtils.isLoginArtist(artistId, session) ||
				ArtistSessionUtils.isLoginArtist("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
								
				return "/myPage/update.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
//			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//				new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
//			return "/myPage/myPage.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
		}
		
		
		//POST request (회원정보가 parameter로 전송됨)
		
//		String projectPath = "E:\\hw\\6\\databaseproject\\newclone";
//		String filePath = ".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\happy\\sample";
//		String imgPath = projectPath + "\\" + filePath;
		
		request.setCharacterEncoding("UTF-8");
		String filename = null; 
		String realFolder = request.getServletContext().getRealPath("sample"); 
		int maxSize = 1024*1024*5; 
		
		MultipartRequest multi = null;
		try{ 
			multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy()); 
			String file1 = (String) multi.getFileNames().nextElement(); 
			filename = multi.getFilesystemName(file1); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 
		
		Artist updateArtist = new Artist(
				artistId,
				artist.getPw(),
				artist.getNickname(),
				multi.getParameter("profile"),
				filename);
		try {
			artistDAO.update(updateArtist);
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 

		request.setAttribute("artist", updateArtist);
		return "redirect:/mypage?artistId=" + artistId;
	}

}
