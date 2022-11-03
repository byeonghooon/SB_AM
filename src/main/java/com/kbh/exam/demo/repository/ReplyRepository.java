package com.kbh.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kbh.exam.demo.vo.Reply;

@Mapper
public interface ReplyRepository {
	@Insert("""
			<script>
				INSERT INTO reply
				SET regDate = NOW(),
				updateDate = NOW(),
				memberId = #{actorId},
				relTypeCode = #{relTypeCode},
				relId = #{relId},
				`body` = #{body}

			</script>
			""")
	public void writeReply(int actorId, String relTypeCode, int relId, String body);

	@Select("""
			<script>
				SELECT LAST_INSERT_ID()
			</script>
			""")
	public int getLastInsertId();

	@Select("""
			<script>
				SELECT R.*, M.nickname AS extra__writerName
				FROM reply AS R
				LEFT JOIN `member` AS M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId} 
				ORDER BY R.id ASC
			</script>
			""")
	public List<Reply> getForPrintReplies(String relTypeCode, int relId);

	
	public Reply getForPrintReply(int actorId, int id);

}