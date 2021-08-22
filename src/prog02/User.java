package prog02;

public class User {

    public final static String FORMAT = "%-40s %-11s %-10d %-17s %-30s";

    private String name;
    private int age;
    private Gender gender;
    private String country;
    private String mail;


    public User(String name, int age, Gender gender, String country, String mail) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }


    public Gender getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getMail() {
        return mail;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name, gender.getNameRus(), age, country, mail);
    }
}
