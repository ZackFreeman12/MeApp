package ca.mohawk.meapp1_0;

public class ProfileModel {
    private String userName;
    private String age;
    private String email;
    private String pass;
    private boolean newUser;
    private String datePref;
    private String gender;
    private String answer;

    public ProfileModel(String userName, String age, String email, String pass, String gender, boolean newUser, String datePref, String answer) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.pass = pass;
        this.newUser = newUser;
        this.datePref = datePref;
        this.gender = gender;
        this.answer = answer;


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

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public String getDatePref() {
        return datePref;
    }

    public void setDatePref(String datePref) {
        this.datePref = datePref;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", newUser=" + newUser +
                ", datePref='" + datePref + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
