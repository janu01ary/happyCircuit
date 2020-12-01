package controller.findArtist;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class CreatePostController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		HttpSession session = request.getSession();
		System.out.println("(CreatePostController) session: " + session);
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			System.out.println("(CreatePostController) session: " + session);
			return "redirect:/findArtist/list";	
        }
		
		// ������ ����Ͽ� ������� id �� nickname ���
		String artistId = ArtistSessionUtils.getLoginArtistId(session);

		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId�� ����ؼ� postCategoryName�� �޾ƿ���
		int postCategoryId = Integer.parseInt((String) request.getParameter("postCategoryId"));
		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
		
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		System.out.println("(CreatePostController) postTitle: " + postTitle);
		System.out.println("(CreatePostController) postContent: " + postContent);
		
		// postAttachment resources�� �߰� �ʿ�
//		String postAttachment = "÷������"; 
			
		String postAttachmentRoute = request.getParameter("postAttachment");
		String postAttachment;
		if (postAttachmentRoute.length() == 0) {
			postAttachment = "÷�����Ͼ���";
		} 
		else {
			postAttachment = postAttachmentRoute.substring(postAttachmentRoute.lastIndexOf("\\") + 1);
		}
		System.out.println("(CreatePostController) postAttachmentRoute ũ��: " + postAttachmentRoute.length() );
		System.out.println("(CreatePostController) postAttachmentRoute: ����" + postAttachmentRoute + "��");
		System.out.println("(CreatePostController) postAttachment: ����" + postAttachment + "��");
//		String nickname = "artist1"; // ����� �α��� �߰� ���ؼ� ���� ���������� ���߿��� �Ʒ�ó�� �ڵ� ¥�����
		String nickname = artist.getNickname(); 
		System.out.println("(CreatePostController) postAttachment: " + postAttachment);
		System.out.println("(CreatePostController) nickname: " + nickname);
		
		
		
//		String projectPath = "D:\\2020\\2�б�\\�����ͺ��̽����α׷���(��ǻ���а�)\\������Ʈ\\PROJECT";
//		String filePath = ".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\happy\\resources\\findArtist";
//		String imgPath = projectPath + "\\" + filePath;
//		System.out.println("(CreatePostController) imgPath:" + projectPath + "\\" + filePath);
//		
//		request.setCharacterEncoding("UTF-8");
//		String realFolder = ""; 
//		String filename = ""; 
//		int maxSize = 1024*1024*5; 
//		String encType = "UTF-8"; 
//		String savefile = "\\resources\\findArtist"; 
//		ServletContext scontext = request.getServletContext(); 
//		System.out.println("(CreatePostController) scontext:" + scontext);
//		realFolder = scontext.getRealPath(savefile); 
//		System.out.println("(CreatePostController) realFolder:" + realFolder);
//		MultipartRequest multi = null;
//		 
//		try{ 
//			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy()); 
//			Enumeration<?> files = multi.getFileNames(); 
//			String file1 = (String)files.nextElement(); 
//			filename = multi.getFilesystemName(file1); 
//			System.out.println("(CreatePostController) filename: " + filename);
//		} catch(Exception e) { 
//			e.printStackTrace(); 
//		} 
		
//		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
//		File Folder = new File(uploadPath);
//		System.out.println(uploadPath);
//		if (!Folder.exists()) {
//			try{
//			    Folder.mkdir(); //���� ����
//			    System.out.println("������ �����Ǿ����ϴ�.");
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}
//		}
//	
//		int maxSize =1024 *1024 *10 * 10;
//	   
//	    MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());	    
//	    
//	    String fileName = multi.getFilesystemName("postAttachment"); //���ϸ�
////		String postAttachmentPath = "../postAttachment/" + fileName;
////	    String postAttachmentPath = uploadPath + "/" + fileName;
//	    String postAttachmentPath = fileName;
//	    System.out.println("postAttachmentPath: " + postAttachmentPath);
//		
//		// ������ ����Ͽ� ������� id �� nickname ���
//		String artistId = ArtistSessionUtils.getLoginArtistId(session);
//		Artist artist = artistDAO.findArtistById(artistId);
//		System.out.println("(CreatePostController) post(artistId): " + artistId);
//		
//		// postCategoryId�� ����ؼ� postCategoryName�� �޾ƿ���
//		int postCategoryId = Integer.parseInt((String) multi.getParameter("postCategoryId"));
//		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
//		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
//		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
//		
//		String nickname = artist.getNickname(); 
//		
//		String postTitle = multi.getParameter("postTitle");
//		String postContent = multi.getParameter("postContent");
//		System.out.println("(CreatePostController) postTitle: " + postTitle);
//		System.out.println("(CreatePostController) postContent: " + postContent);
		
		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachment, 
				postCategoryId, postCategoryName,
				artistId, nickname); // �α��� �κ� �����Ǹ� �̺κ� ���� �ؾ���

		
//		Post post = new Post(0, postTitle,
//				null, 0,
//				postContent, postAttachmentPath, 
//				postCategoryId, postCategoryName,
//				artistId, nickname); // �α��� �κ� �����Ǹ� �̺κ� ���� �ؾ���
		
		System.out.println("(CreatePostController) post: " + post);
		System.out.println("(CreatePostController) post(getPostTitle): " + post.getPostTitle());
		System.out.println("(CreatePostController) post(getPostContent): " + post.getPostContent());
		System.out.println("(CreatePostController) post(getPostCategoryId): " + post.getPostCategoryId());
		
		int postId = postDAO.create(post);
		System.out.println("(CreatePostController) postId: " + postId);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

		return "redirect:/findArtist/view/post?postId=" + postId;
		
	}

}