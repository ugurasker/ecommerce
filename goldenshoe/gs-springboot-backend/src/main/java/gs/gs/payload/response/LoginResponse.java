package gs.gs.payload.response;

import gs.gs.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private User user;
    private String jwt;
}