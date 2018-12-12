package cn.edu.nju.form;

/**
 * @author hongchuanwang
 */
public class LoginFormBuilder {
    private String username;
    private String password;

    public LoginFormBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginFormBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginForm createLoginForm() {
        return new LoginForm(username, password);
    }
}