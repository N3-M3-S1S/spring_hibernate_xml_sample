package com.nemesis.spring_hibernate_xml_sample;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Car;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Order;
import com.nemesis.spring_hibernate_xml_sample.model.service.CarService;
import com.nemesis.spring_hibernate_xml_sample.model.service.DriverService;
import com.nemesis.spring_hibernate_xml_sample.model.service.OrderService;
import com.nemesis.spring_hibernate_xml_sample.model.service.impl.CarService_impl;
import com.nemesis.spring_hibernate_xml_sample.model.service.impl.DriverService_impl;
import com.nemesis.spring_hibernate_xml_sample.model.service.impl.OrderService_impl;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    static DriverService driverService;
    static CarService carService;
    static OrderService orderService;

    public static void main(String[] args) {
        initializeServices();
        createDrivers();
        createCars();
        createOrders();
        tryToCreateDriversWithErrors();
        tryToCreateCarsWithErrors();
        tryToCreateOrderWithErrors();
        setCarsToDrivers();
        setOrdersToDrivers();
        printDriversInfo();
        printCarsByModel("DeLorean");
        printOrdersByAddress("Norman street");
        printDriversCountByName("John");
        deleteRandomDriver();
    }

    static void initializeServices() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_context.xml");
        driverService = ctx.getBean(DriverService_impl.class);
        carService = ctx.getBean(CarService_impl.class);
        orderService = ctx.getBean(OrderService_impl.class);
    }

    static void createDrivers() {
        Map<String, String> licenseNumberName = new HashMap();
        licenseNumberName.put("23735", "John Taylor");
        licenseNumberName.put("82739", "Daniel Chase");
        licenseNumberName.put("35237", "Harold Zimmerman");
        licenseNumberName.put("77912", "John Brown");
        licenseNumberName.forEach((licenseNumber, driverName) -> {
            driverService.createDriver(driverName, licenseNumber);
        });

    }

    static void createCars() {
        Map<String, String> carNumberModel = new HashMap();
        carNumberModel.put("478954abc", "Dodge Charger");
        carNumberModel.put("715126cde", "Ferrari 250 gto");
        carNumberModel.put("628461xyz", "DeLorean DMC 12");
        carNumberModel.put("998267qwe", "DeLorean DMC 12");//cars can have same models
        carNumberModel.forEach((carNumber, carModel) -> {
            carService.createCar(carModel, carNumber);
        });
    }

    static void createOrders() {
        String[] addresses = new String[]{"Norman street/3", "Hill Haven Drive/51", "Columbia Boulevard/122", "Coal Street/2569", "Norma Avenue/4486"};
        for (int i = 0; i < 12; i++) {
            int randomFromAddressIndex;
            int randomToAddressIndex;
            randomFromAddressIndex = ThreadLocalRandom.current().nextInt(0, addresses.length);
            //Generate random address index until it different from another.
            do {
                randomToAddressIndex = ThreadLocalRandom.current().nextInt(0, addresses.length);
            } while (randomToAddressIndex == randomFromAddressIndex);
            String addressFrom = addresses[randomFromAddressIndex];
            String addressTo = addresses[randomToAddressIndex];
            orderService.createOrder(addressFrom, addressTo);
        }
    }

    static void tryToCreateDriversWithErrors() {
        Map<String, String> licenseNumberName = new HashMap();
        licenseNumberName.put("887264", "John Taylor");//invalid license number
        licenseNumberName.put("99878", "Donald 2Adams");//invalid driver name
        licenseNumberName.put("23735", "John Taylor");//duplicate license number
        licenseNumberName.forEach((licenseNumber, driverName) -> {
            driverService.createDriver(driverName, licenseNumber);
        });
    }

    static void tryToCreateCarsWithErrors() {
        Map<String, String> carNumberModel = new HashMap();
        carNumberModel.put("zxc882172", "Ferrari 365 GTS/4");//ivalid car number
        carNumberModel.put("478954abc", "DeLorean DMC 12");//duplicate car number
        carNumberModel.forEach((carNumber, carModel) -> {
            carService.createCar(carModel, carNumber);
        });
    }

    static void tryToCreateOrderWithErrors() {
        String invalidAddressFrom = "Coal Street 2569";//invalid address format
        String invalidAddressTo = "51/Hill Haven Drive";//invalid address format
        orderService.createOrder(invalidAddressFrom, "test/123");
        orderService.createOrder("test/123", invalidAddressTo);
    }

    static void setCarsToDrivers() {
        List<Car> cars = carService.getAllCars();
        List<Driver> drivers = driverService.getAllDrivers();
        if (cars.size() == drivers.size()) {
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                Driver driver = drivers.get(i);
                carService.setCarToDriver(car, driver);
            }
        }
    }

    static void setOrdersToDrivers() {
        List<Order> orders = orderService.getAllOrders();
        List<Driver> drivers = driverService.getAllDrivers();
        int numberOfOrders = orders.size() / drivers.size();//Split all orders between all drivers. All divers have same number of orders.
        int orderIndex = 0;
        for (Driver driver : drivers) {
            while (driver.getOrders().size() < numberOfOrders) {
                Order order = orders.get(orderIndex++);
                orderService.setOrderToDriver(driver, order);
            }
        }
    }

    static void printDriversInfo() {
        List<Driver> drivers = driverService.getAllDrivers();
        drivers.forEach(driver -> {
            Car driverCar = driver.getCar();
            Set<Order> driverOrders = driver.getOrders();
            StringBuilder builder = new StringBuilder();
            builder.append("Info for driver: %1$s%n");
            builder.append("  License number: %2$s%n");
            builder.append("  Car model: %3$s%n");
            builder.append("  Car number: %4$s%n");
            builder.append("  Orders:%n");
            int orderNumber = 1;
            for (Order order : driverOrders) {
                String orderInfo = String.format("   %1$d From: %2$s%n     To: %3$s%n", orderNumber++, order.getAddressFrom(), order.getAddressTo());
                builder.append(orderInfo);
            }
            System.out.printf(builder.toString(), driver.getName(), driver.getLicenseNumber(), driverCar.getCarModel(), driverCar.getCarNumber());
        });
    }

    static void printCarsByModel(String carModel) {
        List<Car> carsByModel = carService.getCarsByModel(carModel);
        System.out.printf("There are %d cars with model '%s'%n", carsByModel.size(), carModel);
        carsByModel.forEach(car -> System.out.printf("  Car number %s%n", car.getCarNumber()));

    }

    static void printOrdersByAddress(String address) {
        List<Order> ordersByAddressFrom = orderService.getAllOrdersByFromAddress(address);
        List<Order> ordersByAddressTo = orderService.getAllOrdersByToAddress(address);
        String s = "  Address from: %1$s Address To: %2$s%n";
        System.out.printf("There are %d orders with address from %s%n", ordersByAddressFrom.size(), address);
        ordersByAddressFrom.forEach(order -> System.out.printf(s, order.getAddressFrom(), order.getAddressTo()));
        System.out.printf("There are %d orders with address to %s%n", ordersByAddressTo.size(), address);
        ordersByAddressTo.forEach(order -> System.out.printf(s, order.getAddressFrom(), order.getAddressTo()));

    }

    static void printDriversCountByName(String driverName) {
        List<Driver> driversByName = driverService.getDriversByName(driverName);
        System.out.printf("There are %d drivers with name %s%n", driversByName.size(), driverName);
        driversByName.forEach(driver -> System.out.printf("  Full name:%1$s License number: %2$s%n", driver.getName(), driver.getLicenseNumber()));
    }

    static void deleteRandomDriver() {
        List<Driver> drivers = driverService.getAllDrivers();
        Collections.shuffle(drivers);
        Driver randomDriver = drivers.get(0);
        System.out.printf("Driver %s is being deleted%n", randomDriver.getName());
        driverService.deleteDriver(randomDriver);
    }
}
