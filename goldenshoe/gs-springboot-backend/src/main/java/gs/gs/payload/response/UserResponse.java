package gs.gs.payload.response;

import gs.gs.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    List<User> users;
}
