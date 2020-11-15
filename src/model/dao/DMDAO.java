package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Artist;
import model.DM;
import model.Message;

public class DMDAO {
	private JDBCUtil jdbcUtil = null;
	
	public DMDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//DM ���̺��� ���ο� DM ���� �� Membership ����
	public int createDMAndMembership(DM dm, List<Artist> artistList) throws SQLException {
		String sql = "INSERT INTO DM VALUES (dmId_seq.nextval)";		

		int result = 0;
		String key[] = {"dmId"};	// PK �÷��� �̸�     
		try {				
			result = jdbcUtil.executeUpdate();	// insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		dm.setDmId(generatedKey); 	// id�ʵ忡 ����  
		   	}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		
		for (int i = 0; i < 2; i++) {
			sql = "INSERT INTO Membership VALUES (id.seq_nextval, ?, ?)";
			Object[] param = new Object[] {dm.getDmId(), artistList.get(i).getArtistId()}; 
			jdbcUtil.setSqlAndParameters(sql, param);
			
			try {				
				result += jdbcUtil.executeUpdate();	// insert �� ����
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource ��ȯ
			}		
		}
		
		return result;		//���������� ó���Ǹ� result == 3	
	}
	
	//� artist�� � DM�� �����ϸ�(ä�ù� ������ ����) Membership���� ����
	//membership�� �ش� dmid�� �ϳ��� ������ dm ���̺������� dmid�� ����
	public int deleteMembership(Artist artist, DM dm) throws SQLException {
		String sql = "DELETE FROM Membership WHERE artistId=? AND dmID=?";		
		Object[] param = new Object[] {artist.getArtistId(), dm.getDmId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		int result = 0;
		try {				
			result = jdbcUtil.executeUpdate();	// insert �� ����
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return result;
	}
	
	//DM�� ���� artist ���
	public List<Artist> findArtistListFromMembership(int dmId) throws SQLException {
		String sql = "SELECT artistId, password, nickname, profile, image "
					+ "FROM Membership m JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE dmId=?";
		Object[] param = new Object[] {dmId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<Artist> artistList = new ArrayList<Artist>();
			while (rs.next()) {
				Artist artist = new Artist(
						rs.getString("artistId"), rs.getString("password"),
						rs.getString("nickname"), rs.getString("profile"),
						rs.getString("image"));
				artistList.add(artist);
				return artistList;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//�ش� artist�� ���� DM ���
	public List<DM> findDMList(Artist artist) throws SQLException {
		String sql = "SELECT dmId FROM Membership WHERE artistId=? ORDER BY id desc ";
		Object[] param = new Object[] {artist.getArtistId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			List<DM> dmList = new ArrayList<DM>();
			while (rs.next()) {
				int dmId = rs.getInt("dmId");
				DM dm = new DM(dmId, findArtistListFromMembership(dmId));
				dmList.add(dm);
			}
			return dmList;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
	
	//Message ����
	public int createMessage(Message msg) throws SQLException {
		String sql = "INSERT INTO Message VALUES (msgId_seq.nextval, ?, ?, ?, ?)";	
		Object[] param = new Object[] {msg.getMessage(), msg.getSentTime(),
						msg.getArtist().getArtistId(), msg.getDm().getDmId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		int result = 0;
		String key[] = {"msgId"};
		try {				
			result = jdbcUtil.executeUpdate();	// insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		msg.setMsgId(generatedKey); 	// id�ʵ忡 ����  
		   	}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return result;
	}
	 
	//�ش� DM���� Message ����Ʈ
	public List<Message> findMessageList(DM dm) throws SQLException {
		String sql = "SELECT msgId, message, sentTime, artistId, password, nickname, profile, image "
					+ "FROM Message m JOIN DM d ON m.dmId = d.dmId "
					+ "JOIN Artist a ON m.artistId = a.artistId "
					+ "WHERE d.dmId=?";
		Object[] param = new Object[] {dm.getDmId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Message> msgList = new ArrayList<Message>();	
			while (rs.next()) {
				Artist artist = new Artist(rs.getString("artistId"),
								rs.getString("password"), 
								rs.getString("nickname"),
								rs.getString("profile"), 
								rs.getString("image"));
				Message msg = new Message(rs.getInt("msgId"),
							rs.getString("message"), 
							new Date(rs.getDate("sentTime").getTime()),
							artist, dm);
				msgList.add(msg);
				return msgList;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}	
		return null;
	}
}