package com.example.lab1;

import com.example.lab1.entity.Car;
import com.example.lab1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.lab1.service.CarService;
import com.example.lab1.service.CustomerService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private CustomerService customerService;
    private CarService carService;

    @Autowired
    public CommandLine(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nAvailable commands:");
            System.out.println("\"1\" - List all");
            System.out.println("\"2\" - List customers");
            System.out.println("\"3\" - List cars");
            System.out.println("\"4\" - Create");
            System.out.println("\"5\" - Delete");
            System.out.println("\"6\" - Exit");

            int input = scanner.nextInt();

            switch (input) {
                case (1):
                    customerService.findAll().forEach(System.out::println);
                    System.out.println("--------------------------------------------");
                    carService.findAll().forEach(System.out::println);
                    break;
                case (2):
                    customerService.findAll().forEach(System.out::println);
                    break;
                case (3):
                    carService.findAll().forEach(System.out::println);
                    break;
                case (4):
                    System.out.println("\"1\" - Create customer");
                    System.out.println("\"2\" - Create car");

                    input = scanner.nextInt();

                    if (input == 1) {
                        System.out.println("Enter new customer id:");
                        int id;
                        while (true) {
                            if (scanner.hasNextInt()) {
                                id = scanner.nextInt();
                                if (id > 0 && id > customerService.findAll().size()) break;
                            }
                            System.out.println("Please enter a valid new customer id:");
                            scanner.nextLine();
                        }
                        System.out.println("Enter new customer name:");
                        String name = scanner.next();
                        System.out.println("Enter new customer surname:");
                        String surname = scanner.next();
                        customerService.create(Customer.builder()
                                .id(customerService.findAll().size() + 1)
                                .name(name)
                                .surname(surname)
                                .build());
                        System.out.println("Customer creation succeeded");
                    } else if (input == 2) {
                        System.out.println("Enter new car registration:");
                        String registration = scanner.next();
                        if (carService.find(registration).isEmpty()) {
                            System.out.println("Enter new car mark:");
                            String mark = scanner.next();
                            System.out.println("Enter new car production year:");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a number:");
                                scanner.next();
                            }
                            int productionYear = scanner.nextInt();

                            Car car = Car.builder()
                                    .registration(registration)
                                    .mark(mark)
                                    .productionYear(productionYear)
                                    .build();

                            System.out.println("Enter new car owner's id:");
                            int id;
                            while (true) {
                                if (scanner.hasNextInt()) {
                                    id = scanner.nextInt();
                                    if (id > 0 && id <= customerService.findAll().size()) break;
                                }
                                System.out.println("Please enter a valid car's owner id:");
                                scanner.nextLine();
                            }

                            if(customerService.find(id).isPresent()) {
                                car.setCustomer(customerService.find(id).get());
                            }


                            carService.create(car);
                            System.out.println("Car creation succeeded");
                        } else System.out.println("The car registration \"" + registration + "\" is not unique");
                    }

                    break;
                case (5):
                    System.out.println("\"1\" - Delete customer");
                    System.out.println("\"2\" - Delete car");

                    input = scanner.nextInt();

                    if (input == 1) {
                        System.out.println("Enter customer id:");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Please enter a number:");
                            scanner.next();
                        }
                        int id = scanner.nextInt();
                        customerService.find(id).ifPresentOrElse(
                                customer -> {
                                    customerService.delete(customer.getId());
                                    System.out.println("Customer removing succeeded");
                                },
                                () -> System.out.println("The customer with id \"" + id + "\" does not exist")
                        );
                    } else if (input == 2) {
                        System.out.println("Enter car registration:");
                        String registration = scanner.next();

                        carService.find(registration).ifPresentOrElse(
                                car -> {
                                    carService.delete(car.getRegistration());
                                    System.out.println("Car removing succeeded");
                                },
                                () -> System.out.println("The car with registration \"" + registration + "\" does not exist")
                        );
                    }
                    break;
                case (6):
                    return;
            }

        }

    }
}
