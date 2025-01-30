import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ReaderDataBase {
    //Класс отвечает за взаимодействие программы с БД

    //Данные для подключения локальной БД
    private final String LocalURL = "jdbc:mysql://localhost:3306/hbdatabase";
    private final String LocalUSERNAME = "root";
    private final String LocalPASSWORD = "root";
    //Данные для подключения другой БД
    private String URL;
    private String USERNAME;
    private String PASSWORD;

    //Если не удалось подключиться к другой БД и пропустить шаг подключения к БД, то
    //Программа при выходе будет подключаться к локальной БД для выгрузки данных
    private boolean localHost;

    //Конструктор для локальной БД
    public ReaderDataBase() {
        localHost = true;
    }

    //Конструктор для другой БД
    public ReaderDataBase(ArrayList<String> listHost) {
        this.URL = listHost.get(0);
        this.USERNAME = listHost.get(1);
        this.PASSWORD = listHost.get(2);
        localHost = false;
    }

    public boolean getLocalHost() {
        return localHost;
    }

    //Методы connectDataBase и ReadPersons осуществляют подключение и чтение данных с БД
    public LinkedList<Person> connectDataBase() {
        LinkedList<Person> persons = new LinkedList<>();
        try{
            if(localHost)
                return ReadPersons(LocalURL, LocalUSERNAME, LocalPASSWORD);
            else
                return ReadPersons(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            localHost = true;
            return null;
        }
    }
    private LinkedList<Person> ReadPersons (String URL, String USERNAME, String PASSWORD) throws SQLException {
        LinkedList<Person> persons = new LinkedList<>();
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSetTable = statement.executeQuery("SHOW TABLES LIKE 'hbtable'");
        if(!resultSetTable.next()){
            throw new SQLException();
        }
        ResultSet resultSet = statement.executeQuery("SELECT * FROM HBTable");
        while(resultSet.next()){
            String Name = resultSet.getString("Name");
            String Surname = resultSet.getString("Surname");
            Date BirthDate = resultSet.getDate("dateofbirth");
            BirthDate.setYear(BirthDate.getYear() + 1900);
            Person person = new Person(Name, Surname, BirthDate);
            System.out.println(person.getName());
            persons.add(person);
        }
        connection.close();
        return persons;
    }

    //Методы disconnectDataBase и WritePerson осуществляют подключение и вывод данных в БД
    public boolean disconnectDataBase(LinkedList<Person> persons) {
        try{
            if(localHost)
                WritePerson(LocalURL, LocalUSERNAME, LocalPASSWORD, persons);
            else
                WritePerson(URL, USERNAME, PASSWORD, persons);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    private void WritePerson(String URL, String USERNAME, String PASSWORD, LinkedList<Person> persons) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM hbtable");
        int id = 1;
        for(Person person : persons){
            statement.executeUpdate("INSERT INTO hbtable (id, name, surname, dateofbirth) VALUES ('"+id+"','"+person.getName()+"','"+person.getSurname()+"',{d'"+person.getBirthdayString()+"'})");
            id++;
        }
        connection.close();
    }
}
