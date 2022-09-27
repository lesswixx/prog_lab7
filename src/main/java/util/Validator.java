package util;

import Exceptions.ArgumentFromFileException;
import data.*;
import sun.security.krb5.internal.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Validator {
    private final Map<String,Command> commandMap = new HashMap<>();
    private final Console console;

    public Validator(Console aConsole){
        console = aConsole;
        initMap();
    }

    public Command validateCommand(String command){
        return commandMap.getOrDefault(command,null);
    }

    public Object validateArgument(Command command) throws ArgumentFromFileException{
        String commandName = command.getCommandName();
        if(console.readFromFileStatus){
           return validateArgumentFromFile(command);
        }
        try {
            if(commandName.contains("add")){
                System.out.println("\tEnter the organization name:");
                String name = console.nextLine();
                System.out.println("Enter the x (x>570) coordinate:");
                Double x = (Double) (read(Double::valueOf));
                while (x > 570){
                    System.out.println("Input error, try again");
                    System.out.println("Enter the x (>570)");
                    x = (Double) (read(Double::valueOf));                }
                System.out.println("Enter the y coordinate:");
                Float y = (Float) (read(Float::valueOf));
                LocalDate creationDate = LocalDate.now();
                System.out.println("Enter the Annual Turnover ");
                Double turnover = (Double) (read(Double::valueOf));//
                System.out.println("Enter the full name ");
                String fullName = console.nextLine();
                System.out.println("Enter the employees count (>=0)");
                Integer employeesCount = (Integer) read(Integer::valueOf);
                while (employeesCount<0){
                    System.out.println("Input error, try again");

                    System.out.println("Enter the employees count");
                    employeesCount = (Integer) read(Integer::valueOf);
                }
                System.out.println("Select and Enter the organization type:");
                for (OrganizationType type : OrganizationType.values()){
                    System.out.println(type);
                }
                OrganizationType type = (OrganizationType) read(OrganizationType::valueOf);

                System.out.println("Enter the street");
                String street = console.nextLine();

                System.out.println("Enter the zipCode");
                String zipCode = console.nextLine();

                System.out.println("Enter the Town x");
                Long townX = (Long) read(Long::valueOf);

                System.out.println("Enter the Town y");
                Double townY = (Double) read(Double::valueOf);

                System.out.println("Enter the Town z");
                Double townZ = (Double) read(Double::valueOf);
                return new Organization(name, new Coordinates(x,y), creationDate , turnover, fullName, employeesCount,type,
                        new Address(street, zipCode,new Location(townX,townY,townZ)));
            }else {
                if(commandName.equals("execute_script")){
                    System.out.println("Enter the path to the script");
                    return console.nextLine();
                }
                if(commandName.equals("remove_by_id") ){
                    System.out.println("Enter the id");
                    return console.nextLine();
                }
            }

        } catch (Exception e) {
            throw new ArgumentFromFileException("Error in the input data in the file");
        }
        return null;
    }

    public Object validateArgumentFromFile(Command command) throws ArgumentFromFileException {
        String commandName = command.getCommandName();
        try {
            if (commandName.contains("add")) {
                String name = console.nextLine();
                Double x = (Double) (read(Double::valueOf));
                Float y = (Float) (read(Float::valueOf));
                LocalDate creationDate = LocalDate.now();
                Double turnover = (Double) (read(Integer::valueOf));
                if(turnover <= 0){
                    throw new Exception();
                }
                String fullName = console.nextLine();
                Integer employeesCount = (Integer) read(Integer::valueOf);
                if(employeesCount<=0){
                    throw new Exception();
                }
                OrganizationType type = (OrganizationType) read(OrganizationType::valueOf);
                String street = console.nextLine();
                String zipCode = console.nextLine();
                Long townX = (Long) read(Long::valueOf);
                Double townY = (Double) read(Long::valueOf);
                Double townZ = (Double) read(Long::valueOf);
                return new Organization(name, new Coordinates( x,y), creationDate , turnover, fullName, employeesCount,type,
                        new Address(street, zipCode,new Location(townX,townY,townZ)));
            }
            if (commandName.equals("remove_by_id")) {
                System.out.println("\tEnter the id");
                return console.nextLine();
            }
            if (commandName.equals("execute_script")) {
                System.out.println("\tEnter the path to the script");
                return console.nextLine();
            }
        }catch (Exception e){
            throw new ArgumentFromFileException("Error in the input data in the file");
        }
        return null;
    }
    private Object read(Function<String, Object> check){
        while (true) {
            String str = console.nextLine();
            if (!console.readFromFileStatus) {
                try {
                    return check.apply(str);
                } catch (Exception e) {
                    System.out.println("\tInput error, try again");
                    System.out.println("\t" + e.getMessage() + "\n");
                }
            } else {
                return check.apply(str);
            }
        }
    }







    public void initMap() {
        commandMap.put("add", new Command("add"));
        commandMap.put("add_if_min", new Command("add_if_min"));
        commandMap.put("clear", new Command("clear"));
        commandMap.put("execute_script", new Command("execute_script"));
        commandMap.put("exit", new Command("exit"));
        commandMap.put("help", new Command("help"));
        commandMap.put("info", new Command("info"));
        commandMap.put("show", new Command("show"));
        commandMap.put("remove_by_id", new Command("remove_by_id"));
        commandMap.put("min_by_creation_date", new Command("min_by_creation_date"));
        commandMap.put("sum_of_annual_turnover",new Command("sum_of_annual_turnover"));

    }


}
