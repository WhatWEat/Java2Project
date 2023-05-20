package cse.java2.project.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.java2.project.entities.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {
    @Insert("insert into Question values(#{owner}, #{content_license}, #{link}, #{last_activity_date}, #{creation_date}, #{answer_count}, #{title}, #{question_id}, #{score}, #{is_answered}, #{view_count}, #{last_edit_date}")
    int insertQuestion(long owner, String content_license, String link, long last_activity_date, long creation_date, int answer_count, String title, long question_id, int score, boolean is_answered, int view_count, long last_edit_date);

    @Insert("insert into QuestionTag values(#{question_id}, #{tag})")
    int insertQuestionTag(long question_id, String tag);

}
