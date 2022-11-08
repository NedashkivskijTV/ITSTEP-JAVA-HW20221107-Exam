package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        test_AllTables();
    }

    public static void test_AllTables(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // конфігураційний файл, де вказано налаштування доступу до БД
                .addAnnotatedClass(Country.class) // передаються сутності (Entity)
                .addAnnotatedClass(Brand.class) // передаються сутності (Entity)
                .addAnnotatedClass(Car.class) // передаються сутності (Entity)
                .addAnnotatedClass(Service.class) // передаються сутності (Entity)
                .addAnnotatedClass(Client.class) // передаються сутності (Entity)
                .addAnnotatedClass(Manager.class) // передаються сутності (Entity)
                .addAnnotatedClass(Order.class) // передаються сутності (Entity)
                .buildSessionFactory();

        Session session = factory.getCurrentSession(); // створення сесії

        session.beginTransaction(); // відкриття сесії

        // CREATE ---------------------
        // Додавання країни
//        Country country = new Country("France");
//        session.save(country);

        // Додавання країни з брендами
//        Country country1 = new Country("Germany");
//        Brand brand1 = new Brand("Mercedes Benz");
//        Brand brand2 = new Brand("BMW");
//        Brand brand3 = new Brand("Audi");
//        country1.addBrand(brand1);
//        country1.addBrand(brand2);
//        country1.addBrand(brand3);
//        session.save(country1);

        // Додавання брендів до раніше доданої країни при наявності доступу до даної країни
        //Country country2 = session.get(Country.class, 4);
//        List<Country> countryList = session.createQuery("from Country where countryName = 'France'").getResultList();
//        System.out.println(countryList.size());
//        System.out.println(countryList.get(0).getId());
//        Brand brand4 = new Brand("Renault");
//        Brand brand5 = new Brand("Citroen");
//        countryList.get(0).addBrand(brand4);
//        countryList.get(0).addBrand(brand5);

        // Додавання моделей авто до бренда при наявності бренда
//        Brand brand = session.get(Brand.class, 7); // Ferrari
//        //System.out.println(brand);
//        Car car1 = new Car("GTC4Luss", "XTA123456Y1234567", "123132");
//        Car car2 = new Car("F8 Tributo", "XTA321987Y98765412", "951234");
//        brand.addCar(car1);
//        brand.addCar(car2);

        // Додавання сервісу
//        Service service1 = new Service("ТО двигуна", 1000);
//        session.save(service1);

        // Додавання клієнта
//        Client client1 = new Client("Ivan", "Ivanenko", "+380971111111", false);
//        Client client2 = new Client("Anton", "Antonenko", "+380983333333", true);
//        session.save(client1);
//        session.save(client2);

        // Дожавання менеджера
//        Manager manager1 = new Manager("Petro", "Petrenko", "+380662222222");
//        session.save(manager1);

        //  Додавання замовлення при наявності автомобіля, сервіса, клієнта, менеджера
//        Order order = new Order(new GregorianCalendar(2022, Calendar.SEPTEMBER, 10, 10, 00), new GregorianCalendar(2022, Calendar.SEPTEMBER, 12, 18 , 00));
//        Car car = session.get(Car.class, 2);
//        Service service = session.get(Service.class, 1);
//        Client client = session.get(Client.class, 2);
//        Manager manager = session.get(Manager.class, 1);
//        order.setCar(car);
//        order.setService(service);
//        order.setClient(client);
//        order.setManager(manager);
//        session.save(order);

        // UPDATE ---------------------

        // DELETE ---------------------

        // READ ---------------------
        // Виведення усіх авто з інф про бренд та країну
//        List<Car> cars = session.createQuery("from Car").getResultList();
//        //cars.forEach(System.out::println);
//        for (Car car : cars) {
//            System.out.println("-------");
//            System.out.println(car);
//            System.out.println(car.getBrand());
//            System.out.println(car.getBrand().getCountry());
//        }

        // Виведення усіх замовлень із додатковою інф

        List<Order> orders = session.createQuery("from Order").getResultList();
        System.out.println("\n---------- ORDERS ---------------");
        for (Order order : orders) {
            System.out.println(order);
            System.out.println("service - " + order.getService().getServiceName() + " - $" + order.getService().getServicePrice());
            System.out.println("avto - " + order.getCar().getBrand().getBrandName() + " (" + order.getCar().getBrand().getCountry().getCountryName() + ") " + order.getCar().getModelName());
            System.out.println("client - " + order.getClient().getFirstName() + " " + order.getClient().getLastName() + (order.getClient().isVip()? " - VIP client !" : ""));
            System.out.println("manager - " + order.getManager().getFirstName() + " " + order.getManager().getLastName() + " " + order.getManager().getPhone());
            System.out.println("-------");
        }

        session.getTransaction().commit(); // закриття сесії - знімок - збереження в БД раніше зроблених змін , навіть у разі виникнення помилки
    }

}
