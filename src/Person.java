import java.util.Date;

public class Person {
    //Класс хранит информацию об одном контакте: Имя, Фамилию, Дату Рождения
    private String name;
    private String surname;
    private Date birthday = new Date();

    public Person(String name, String surname, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    //Сетеры на каждую переменную
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //Гетеры на каждую переменную
    //getBirthdayString возвращает дату в формате String - "yyyy-MM-dd"
    //getBirthdayStringDDMMYYYY возвращает дату в формате String - "dd/MM/yyyy"
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getBirthdayString() {
        int year = birthday.getYear();
        int month = birthday.getMonth()+1;
        int day = birthday.getDate();
        if(month < 10 && day < 10){
            return year + "-0" + month + "-0" + day;
        }else if (month < 10){
            return year + "-0" + month + "-" + day;
        }else if (day < 10){
            return year + "-" + month + "-0" + day;
        }
        return year + "-" + month + "-" + day;
    }
    public String getBirthdayStringDDMMYYYY() {
        int year = birthday.getYear();
        int month = birthday.getMonth()+1;
        int day = birthday.getDate();
        if (month < 10 && day < 10) {
            return "0" + day + "/0" + month + "/" + year;
        }else if (month < 10) {
            return day + "/0" + month + "/" + year;
        }else if (day < 10) {
            return "0" + day + "/" + month + "/" + year;
        }
        return day + "/" + month + "/" + year;
    }
}
