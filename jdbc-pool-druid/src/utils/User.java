package utils;

/**
 * ClassName: User
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/2 13:31
 * @Version 1.0
 */
public class User {

    private int id;
    private String account;
    private String password;
    private String nickname;

    public User() {
    }

    public User(int id, String account, String password, String nickname) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
