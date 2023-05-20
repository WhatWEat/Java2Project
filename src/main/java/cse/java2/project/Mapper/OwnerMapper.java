package cse.java2.project.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.java2.project.entities.Owner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OwnerMapper extends BaseMapper<Owner> {

    @Insert("insert into Owner values(#{profile_image}, #{account_id}, #{user_type}, #{user_id}, #{link}, #{reputation}, #{display_name}")
    int insertOwner(String profile_image, long account_id, String user_type, long user_id,  String link, int reputation, String display_name);

}
