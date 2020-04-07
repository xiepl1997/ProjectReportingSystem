package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where email = #{email} and password = #{password}")
    User SelectUser(@Param("email") String email, @Param("password") String password);
}
