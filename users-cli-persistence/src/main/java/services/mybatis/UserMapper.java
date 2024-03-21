package services.mybatis;

import models.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface UserMapper {
    @Delete("DELETE FROM users WHERE id=#{userId}")
    int delete(String userId);

    @Select("SELECT * FROM users")
    @MapKey("id")
    Map<String, User> getAllUsers();

    @Select("SELECT * FROM users where id=#{userId}")
    @MapKey("id")
    Map<String, User> getUserById(String userId);

    @Insert("INSERT INTO users (id, name, login, password) VALUES (#{id}, #{name}, #{login}, #{password})")
    void createUser(User user);

    @Insert("UPDATE users SET name=#{name}, login=#{login}, password=#{password} WHERE id=#{id}")
    void updateUser(User user);

    @Select("SELECT * FROM users ORDER BY id LIMIT #{limit} OFFSET #{offset}")
    @MapKey("id")
    Map<String, User> getUsersPaginated(@Param("limit") int limit, @Param("offset") int offset);


}
