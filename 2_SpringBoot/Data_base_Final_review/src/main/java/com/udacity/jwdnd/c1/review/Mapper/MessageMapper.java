
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.udacity.jwdnd.c1.review.model.ChatMessage;

@Mapper
public interface   {
    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getMessage(); 
    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES(#{userName}, #{msgTxt})") 
    @Options(useGeneratedKeys = true, keyProperty = "messageId") 
    int insert(ChatMessage message); 
} 
