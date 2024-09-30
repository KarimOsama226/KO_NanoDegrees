
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = # {userName}")
    User getUser(String username); 
 
    @Insert("INSERT INTO USERS (username, password, firstname, lastname) VALUES(#{userName}, #{passWord}, #{firstName}, #{lastName})") 
    @Options(useGeneratedKeys = true, keyProperty = "userId") 
    int insert(User user); 
} 
