package ca.mohawk.meapp11;

public class ProfileModel {
    private String userName;
    private String age;
    private String email;
    private String pass;

    public ProfileModel(String userName, String age, String email, String pass) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.pass = pass;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public String getAge() {
        return age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
