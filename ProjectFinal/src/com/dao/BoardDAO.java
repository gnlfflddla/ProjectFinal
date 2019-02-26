package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;
import com.dto.CommentDTO;
import com.dto.CommentPageDTO;
import com.dto.PageDTO;

public class BoardDAO {
	// 글자세히 보기
	public BoardDTO boardRetrieve(SqlSession session, String num) {
		// TODO Auto-generated method stub
		BoardDTO dto = new BoardDTO();
		dto = session.selectOne("BoardMapper.boardRetrieve", num);
		readCount(session, num);
		return dto;
	}

	// 조회수 1증가
	public void readCount(SqlSession session, String num) {
		session.update("BoardMapper.boardCnt", num);
	}

	// 페이징 전체, 검색조건에 따른 총 숫자 확인
	public int totalCount(SqlSession session, BoardDTO dto) {
		return session.selectOne("BoardMapper.TotalCount", dto);
	}
	
	public int totalAllCount(SqlSession session) {
		return session.selectOne("BoardMapper.TotalAllCount");
	}

	// 목록 보기
	public PageDTO boardList(SqlSession session, PageDTO pDTO) {
		// TODO Auto-generated method stub
		
		int offset = (pDTO.getCurPage() - 1) * pDTO.getPerPage();
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		list = session.selectList("BoardMapper.boardlist", null, new RowBounds(offset, pDTO.getPerPage()));
		pDTO.setList(list);
		pDTO.setTotalCount(totalAllCount(session));
		return pDTO;
	}

	// 글쓰기
	public int boardWrite(SqlSession session, BoardDTO dto) {
		// TODO Auto-generated method stub
		int result = session.insert("BoardMapper.boardWrite", dto);

		return result;
	}

	// 글 수정하기
	public int boardUpdate(SqlSession session, BoardDTO dto) {
		// TODO Auto-generated method stub
		int result = session.update("BoardMapper.boardUpdate", dto);

		return result;
	}

	// 글 수정하기
	public int boardDelete(SqlSession session, String _num) {
		// TODO Auto-generated method stub
		int result = session.delete("BoardMapper.boardDelete", _num);

		return result;
	}

	// 글 검색하기
	public PageDTO boardSearch(SqlSession session, BoardDTO dto, PageDTO pDTO) {
		List<BoardDTO> list = new ArrayList<>();
		int offset = (pDTO.getCurPage() - 1) * pDTO.getPerPage();
		list = session.selectList("BoardMapper.boardSearch", dto, new RowBounds(offset, pDTO.getPerPage()));
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(session,dto));
		
		return pDTO;
	}

	// 답변글 입력폼 보기
	public BoardDTO boardReplyUI(SqlSession session, String _num) {
		BoardDTO dto = new BoardDTO();
		dto = session.selectOne("BoardMapper.boardReplyUI", _num);
		return dto;
	}

	// 답변글 의 기존 repStep 1증가
	public void makeReply(SqlSession session, BoardDTO dto) {
		session.update("BoardMapper.makeReply", dto);
	}

	// 답변달기
	public void boardReply(SqlSession session, BoardDTO dto) {
		makeReply(session, dto);
		session.insert("BoardMapper.boardReply", dto);
	}
	// 페이징 전체, 검색조건에 따른 총 숫자 확인 댓글 ver
		public int totalCmtCount(SqlSession session, String num) {
			return session.selectOne("CommentMapper.TotalCmtCount",num);
		}

	//댓글 입력
	public void cmtWrite(SqlSession session, CommentDTO cdto) {
		// TODO Auto-generated method stub
		session.insert("CommentMapper.cmtWrite", cdto);
	}
	//댓글목록
	public CommentPageDTO cmtList(SqlSession session, CommentPageDTO cmtPDTO,String num) {
		// TODO Auto-generated method stub
		int offset = (cmtPDTO.getCurPage() - 1) * cmtPDTO.getPerPage();
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = session.selectList("CommentMapper.cmtlist", num, new RowBounds(offset, cmtPDTO.getPerPage()));
		cmtPDTO.setList(list);
		cmtPDTO.setTotalCount(totalCmtCount(session,num));
		return cmtPDTO;
	}

	public void cmtReply(SqlSession session, CommentDTO cdto) {
		// TODO Auto-generated method stub
		int repStep=makeCmtReply(session, cdto);
		System.out.println(repStep);
		
		cdto.setRepStep(repStep);
		session.insert("CommentMapper.cmtReply", cdto);
	}

	private int makeCmtReply(SqlSession session, CommentDTO cdto) {
		// TODO Auto-generated method stub
		return session.selectOne("CommentMapper.makeCmtReply", cdto);
	}
}
