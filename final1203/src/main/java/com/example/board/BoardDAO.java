package com.example.board;

import com.example.board.BoardVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoardDAO {
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public final String BOARD_INSERT = "insert into BOARD (category, title, writer, content) values (?,?,?,?)";
	public final String BOARD_UPDATE = "update BOARD set category=?, title=?, writer=?, content=?, where seq=?";
	public final String BOARD_DELETE = "delete from BOARD where seq=?";
	private final String BOARD_GET = "select * from BOARD where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";


	public int insertBoard (BoardVO vo) {
		return template.update(BOARD_INSERT,
				new Object[]{vo.getCategory(), vo.getTitle(), vo.getWriter(), vo.getContent()});
	}

	public int deleteBoard(int id) {

		return template.update(BOARD_DELETE, new Object[]{id});
	}

	public int updateBoard(BoardVO vo) {
		String BOARD_UPDATE = "update BOARD set category='"+vo.getCategory()+"', title='"+vo.getTitle()+"', writer='"+vo.getWriter()+"', content='"+vo.getContent()+"' where seq="+vo.getSeq()+"";
		return template.update(BOARD_UPDATE);
	}

	public BoardVO getBoard(int seq) {
		BoardVO boardVO = template.queryForObject(BOARD_GET,

				new BeanPropertyRowMapper<BoardVO>(BoardVO.class),
				new Object[]{seq}
		);
		System.out.println(boardVO.getTitle()+boardVO.getCategory());
		return boardVO;
	}

	public List<BoardVO> getBoardList(){
		return template.query(BOARD_LIST, new RowMapper<BoardVO>() {

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO data = new BoardVO();
				data.setSeq(rs.getInt("seq"));
				data.setCategory(rs.getString("category"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setRegdate(rs.getDate("regdate"));
				data.setWriter(rs.getString("writer"));
				return data;
			}
		});
	}
}