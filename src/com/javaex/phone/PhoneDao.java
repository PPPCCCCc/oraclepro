package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	
	// 조회
	public List<PersonVo> phoneSelect() {
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "       company ";
			query += " from person ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				phoneList.add(personVo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return phoneList;
	}

	// 등록
	public int personInsert(String name, String hp, String company) {
		int count = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ? ,? ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 등록되었습니다");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
	
	//수정
	public int personUpdate(String name, String hp, String company, int personId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			pstmt.setInt(4, personId);

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 수정되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	// 삭제
	public int personDelete(int personId) {
		int count = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from person";
			query += " where person_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return personId;
	}
}
