package by.bstu.besman.lw3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Serializable, Parcelable {

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEducation() {
        return education;
    }

    public String getEd_degree() {
        return ed_degree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEd_degree(String ed_degree) {
        this.ed_degree = ed_degree;
    }

    private String name;
    private String surname;
    private int age;

    private String country;
    private String city;

    private String education;
    private String ed_degree;

    User (){}

    User ( String name,String surname, int age,
         String country, String city,
         String education,String ed_degree){

        this.name = name;
        this.surname = surname;
        this.age = age;

        this.country = country;
        this.city = city;

        this.education = education;
        this.ed_degree = ed_degree;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User( source.readString(), source.readString(), source.readInt(),
            source.readString(), source.readString(),
            source.readString(),source.readString());
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(age);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(education);
        dest.writeString(ed_degree);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", education='" + education + '\'' +
                ", ed_degree='" + ed_degree + '\'' +
                '}';
    }
}
