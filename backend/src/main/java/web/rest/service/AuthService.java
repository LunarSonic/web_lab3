package web.rest.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import web.dao.UserDAO;
import web.entity.User;
import web.exception.ApplicationException;
import web.exception.ApplicationError;
import web.utility.HashingUtil;
import web.utility.JwtUtil;

@ApplicationScoped
public class AuthService {

    @Inject
    private UserDAO userDAO;

    private final JwtUtil jwtUtil = new JwtUtil();

    public void register(String username, String password) {
        if (userDAO.findUserByUsername(username) != null) {
            throw new ApplicationException(ApplicationError.INVALID_CREDENTIALS);
        }
        String hashedPassword = HashingUtil.hashPassword(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userDAO.saveNewUser(user);
    }

    public Tokens login(String username, String password) {
        System.out.println("Попытка войти " + username);
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            throw new ApplicationException(ApplicationError.INVALID_CREDENTIALS);
        }
        boolean passwordValid = HashingUtil.verifyPassword(password, user.getPassword());
        if (!passwordValid) {
            throw new ApplicationException(ApplicationError.INVALID_CREDENTIALS);
        }
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userDAO.updateRefreshToken(user.getId(), refreshToken);
        System.out.println("Вход успешно выполнен для пользователя " + username);
        return new Tokens(accessToken, refreshToken);
    }

    public String refresh(String refreshToken) {
        if (refreshToken == null) {
            throw new ApplicationException(ApplicationError.NO_TOKEN);
        }
        String username = jwtUtil.getUsernameFromToken(refreshToken, true);
        if (username == null) {
            throw new ApplicationException(ApplicationError.INVALID_TOKEN);
        }
        User user = userDAO.findUserByRefreshToken(refreshToken);
        return jwtUtil.generateAccessToken(user);
    }

    public void logout(String refreshToken) {
        if (refreshToken == null) {
            return;
        }
        User user = userDAO.findUserByRefreshToken(refreshToken);
        if (user != null) {
            userDAO.clearRefreshToken(user.getId());
        }
    }

    public record Tokens(String accessToken, String refreshToken) {}
}
