package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.configuration.MySqlSesstionFactory;
import com.dao.BoardDAO;
import com.dto.BoardDTO;
import com.dto.CommentDTO;
import com.dto.CommentPageDTO;
import com.dto.PageDTO;

public class BoardService {
	
	public BoardDTO boardRetrieve(String _num) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		BoardDTO dto = new BoardDTO();
		try {
			BoardDAO dao = new BoardDAO();
			dto = dao.boardRetrieve(session,_num);
			session.commit();		//DAO에서 조회수도 함께 update시켜주므로 commit을 사용해야한다
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return dto;
	}
	public BoardDTO boardReplyUI(String _num) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		BoardDTO dto = new BoardDTO();
		try {
			BoardDAO dao = new BoardDAO();
			dto = dao.boardReplyUI(session,_num);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return dto;
	}
	public PageDTO boardList(PageDTO pDTO) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			pDTO = dao.boardList(session,pDTO);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return pDTO;
	}
	public int boardWrite(BoardDTO dto) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		int result=0;
		try {
			BoardDAO dao = new BoardDAO();
			result = dao.boardWrite(session,dto);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return result;
	}
	public int boardUpdate(BoardDTO dto) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		int result=0;
		try {
			BoardDAO dao = new BoardDAO();
			result = dao.boardUpdate(session,dto);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return result;
	}
	public int boardDelete(String _num) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		int result=0;
		try {
			BoardDAO dao = new BoardDAO();
			result = dao.boardDelete(session,_num);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return result;
	}
	public PageDTO boardSearch(BoardDTO dto, PageDTO pDTO) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			pDTO = dao.boardSearch(session,dto,pDTO);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return pDTO;
	}
	public void boardReply(BoardDTO dto) {
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			dao.boardReply(session,dto);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
	}
	//comment 
	public void cmtWrite(CommentDTO cdto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			dao.cmtWrite(session,cdto);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
	}
	public void cmtReply(CommentDTO cdto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			dao.cmtReply(session,cdto);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
	}
	public CommentPageDTO cmtList(CommentPageDTO cmtPDTO,String num) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSesstionFactory.getSesstion();
		try {
			BoardDAO dao = new BoardDAO();
			cmtPDTO = dao.cmtList(session,cmtPDTO,num);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return cmtPDTO;
	}

}
