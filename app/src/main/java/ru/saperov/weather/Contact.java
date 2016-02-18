package ru.saperov.weather;

/**
 * Created by saperov on 10.02.16.
 */
public class Contact {
    private int _id;
    private String mName;
    private String mPhoneNumber;
    private int mAge;

    // Пустой констуктор
    public Contact() {

    }

    // Конструктор с параметрами
    public Contact(int id, String name, String phonenumber, int age) {
        this._id = id;
        this.mName = name;
        this.mPhoneNumber = phonenumber;
        this.mAge = age;
    }

    // Конструктор с параметрами
    public Contact(String name, String phonenumber, int age) {
        this.mName = name;
        this.mPhoneNumber = phonenumber;
        this.mAge = age;
    }

    // Создание геттеров-сеттеров

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.mPhoneNumber = phonenumber;
    }

    public int getAge() {
        return this.mAge;
    }

    public void setAge(int age) {
        this.mAge = age;
    }
}
