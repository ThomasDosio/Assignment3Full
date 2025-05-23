import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EVEMain
    {
        public static void main(String[] args)
            {


                String[] typesMotor = {"car", "truck", "motorcycle"};

                Scanner keyboard = new Scanner(System.in);

                //makes list of vehicles ot order them by id
                ArrayList<ArrayList<ArrayList<String>>> vehicles =
                        new ArrayList<>();

                while (keyboard.hasNext() && (keyboard.hasNext("vehicle_id") ||
                        keyboard.hasNext("vehicle_id:")))
                    {
                        ArrayList<ArrayList<String>> vehicle =
                                CorrectInput(keyboard);

                        if (vehicle == null) continue;

                        if (vehicles.isEmpty())
                            {
                                vehicles.add(vehicle);
                            } else
                            {
                                for (int k = 0; k < vehicles.size(); k++)
                                    {
                                        if (Integer.parseInt(
                                                vehicles.get(k).get(0).get(1)) >
                                                Integer.parseInt(
                                                        vehicle.get(0).get(1)))
                                            {
                                                vehicles.add(k, vehicle);
                                                break;
                                            } else if (
                                                k == vehicles.size() - 1 &&
                                                        Integer.parseInt(
                                                                vehicles.get(k)
                                                                        .get(0)
                                                                        .get(1)) <=
                                                                Integer.parseInt(
                                                                        vehicle.get(
                                                                                        0)
                                                                                .get(1)))
                                            {
                                                vehicles.add(vehicle);
                                                break;
                                            }
                                    }
                            }
                    }

                for (int i = 0; i < vehicles.size(); i++)
                    {
                        ArrayList<ArrayList<String>> vehicle = vehicles.get(i);
                        if (i != 0)
                            {
                                System.out.println();
                            }
                        PrettyPrint(vehicle);
                        System.out.println();
                    }

            }

        public static ArrayList<ArrayList<String>> CorrectInput(
                Scanner keyboard)
            {
                if (keyboard.hasNext())
                    {
                        //makes a list of the details of the vehicle
                        ArrayList<ArrayList<String>> vehicle =
                                new ArrayList<>();

                        boolean allValidKeys= true;
                        int counter = 0;

                        //for each of the six categories it adds
                        // the key and the info to a list

                        while (keyboard.hasNext() && keyboard.hasNextLine()){

                        String line= keyboard.nextLine().trim();

                        if (line.isEmpty()) { break;}

                            counter++;


                                // creates a list with key and
                                // information
                                ArrayList<String> info = new ArrayList<>();

                                String key = line.substring(0, line.indexOf(
                                        " "));
                                String string = line.substring(line.indexOf(
                                        " "));

                                String[] information = string.split(",");

                                if (key.contains(":"))
                                    {
                                        key = key.substring(0,
                                                key.indexOf(":"));
                                    }

                                String [] Keys = {"vehicle_id","type", "color",
                                        "manufacturer", "engine", "wheels",
                                        "sails"};
                                boolean validKeys = false;

                                for (String s : Keys)
                                    {
                                        if (key.equals(s))
                                            {
                                                validKeys = true;
                                                break;
                                            }
                                    }

                                if (validKeys) info.add(0, key);
                                else allValidKeys = false;

                                for (String s : information)
                                    {
                                        if (validKeys) info.add(s.trim());
                                    }


                                if (!info.isEmpty()) vehicle.add(info);
                            }

                        if (counter == 0) return null;

                        Sort(vehicle);

                        ArrayList<String> cost = new ArrayList<>();
                        cost.add("cost");
                        cost.add(Integer.toString(costCalculator(vehicle)));
                        vehicle.add(cost);

                        Sort(vehicle);

                        ArrayList<String> valid = new ArrayList<>();
                        valid.add("valid");

                        boolean validation;
                        if (vehicle.get(vehicle.size()-1).get(0).equals(
                                "wheels")) validation = validationMotor(vehicle);
                        else validation = validationSea(vehicle);

                        if (allValidKeys && (validation))
                            valid.add(1,
                            "true");
                        else valid.add(1 ,"false");
                        vehicle.add(valid);

                        Sort(vehicle);

                        return vehicle;


                    }
                return null;
            }

        public static void PrettyPrint(ArrayList<ArrayList<String>> vehicle)
            {
                for (int j = 0; j < vehicle.size(); j++)
                    {
                        ArrayList<String> info = vehicle.get(j);
                        if (j!= 0) System.out.println();
                        System.out.print(info.get(0).trim() + ": ");
                        for (int i = 1; i < info.size(); i++)
                            {
                                System.out.print(removeColon(
                                        removeZero(info.get(i).trim())).trim());
                                if (i != info.size() - 1)
                                    {
                                        System.out.print(", ");
                                    }
                            }
                    }
            }

        public static ArrayList<ArrayList<String>> FixFormatting(ArrayList<ArrayList<String>> vehicle)
            {
                for (ArrayList<String> info : vehicle)
                    {
                        for (int i = 1; i < info.size(); i++)
                            {
                                removeColon(removeZero(info.get(i).trim()));
                            }
                    }

                return vehicle;
            }

        public static String removeZero(String str)
            {
                if (str.equals("0"))
                    {
                        return str;
                    }

                int i = 0;
                while (i < str.length() && str.charAt(i) == '0')
                    {
                        i++;
                    }
                return str.substring(i);

            }

        public static String removeColon(String str)
            {
                int i = 0;
                while (i < str.length() && str.charAt(i) == ':')
                    {
                        i++;
                    }
                return str.substring(i).trim();
            }

        public static void Sort(ArrayList<ArrayList<String>> list)
            {
                ArrayList<String> id = list.get(0);
                list.sort(Comparator.comparing(o -> o.get(0).trim()));
                list.remove(id);
                list.add(0, id);
            }

        public static boolean validationMotor(ArrayList<ArrayList<String>> vehicle)
            {
                String[] colors =
                        {"red", "green", "blue", "orange", "yellow", "purple",
                                "pink", "black", "white", "silver", "gold"};
                boolean validColours = isEqual(vehicle, 1, 1, colors);
                String[] manufacturers =
                        {"ACME", "Consolidated Products", "Goliath Inc."};
                boolean validManufacturers =
                        isEqual(vehicle, 4, 1, manufacturers) &&
                                isEqual(vehicle, 3, 1, manufacturers);
                String[] typesMotor = {"car", "truck", "motorcycle"};
                boolean validTypes = isEqual(vehicle, 5, 1, typesMotor);
                String[] carSubtypes = {"hatchback", "sedan", "convertible"};
                String[] truckSubtypes = {"pickup", "eighteen wheeler"};
                String[] motorcycleSubtypes = {"sport", "touring"};
                String[] fuels = {"diesel", "petrol", "electric"};
                String[] truckfuels = {"diesel", "petrol"};
                boolean validSubtypes = (vehicle.get(5).get(1).equals("car") &&
                        isEqual(vehicle, 5, 2, carSubtypes)) ||
                        (vehicle.get(5).get(1).equals("truck") &&
                                isEqual(vehicle, 5, 2, truckSubtypes)) ||
                        (vehicle.get(5).get(1).equals("motorcycle") &&
                                isEqual(vehicle, 5, 2, motorcycleSubtypes));
                boolean validFuels = (vehicle.get(5).get(1).equals("car") &&
                        isEqual(vehicle, 3, 2, fuels)) ||
                        (vehicle.get(5).get(1).equals("truck") &&
                                isEqual(vehicle, 3, 2, truckfuels)) ||
                        (vehicle.get(5).get(1).equals("motorcycle") &&
                                vehicle.get(3).get(2).equals("petrol"));
                String [] wheelTypes = {"winter", "summer", "all-weather"};
                boolean validWheelTypes = isEqual(vehicle, 6, 2, wheelTypes);
                boolean wheelNumberCheck =
                        !vehicle.get(6).get(3).chars().allMatch(Character::isAlphabetic);
                boolean rightNumberWheels = (vehicle.get(5).get(1).equals(
                        "car") && vehicle.get(6).get(3).equals("4")) || (vehicle.get(5).get(1).equals("motorcycle")
                        && vehicle.get(6).get(3).equals("2")) || (vehicle.get(5).get(2).equals("pickup") && vehicle.get(6).get(3).equals("4")) ||
                        (vehicle.get(5).get(2).equals("pickup") && vehicle.get(6).get(3).equals("6")) ||
                        (vehicle.get(5).get(2).equals("eighteen wheeler") && vehicle.get(6).get(3).equals("18"));
                boolean valid =
                        validColours && validManufacturers && validTypes &&
                                validSubtypes && validFuels && validWheelTypes &&
                                wheelNumberCheck && rightNumberWheels;

                if (vehicle.get(5).get(1).equals("motorcycle") && vehicle.get(6).get(1).equals("ACME"))
                    {
                        valid = false;
                    }

                if (vehicle.get(5).get(2).equals("eighteen wheeler") && !vehicle.get(6).get(2).equals("summer"))
                    {
                        valid = false;
                    }

                if (vehicle.get(5).get(2).equals("sedan") && vehicle.get(1).get(1).equals("purple"))
                    {
                        valid = false;
                    }

                if (vehicle.get(5).get(2).equals("pickup") && vehicle.get(1).get(1).equals("gold") && vehicle.get(4).get(1).equals("Goliath Inc."))
                    {
                        valid = false;
                    }

                return valid;
            }

        public static boolean validationSea (ArrayList<ArrayList<String>> vehicle)
            {

                String[] colors =
                        {"red", "green", "blue", "orange", "yellow", "purple",
                                "pink", "black", "white", "silver", "gold"};
                boolean validColours = isEqual(vehicle, 1, 1, colors);
                String[] manufacturers =
                        {"ACME", "Consolidated Products", "Goliath Inc."};
                boolean validManufacturers =
                        isEqual(vehicle, 4, 1, manufacturers);

                boolean validSails;


                String[] typesSea = {"sailing vessel", "powered vessel"};
                boolean sails = vehicle.get(5).get(1).equals("sailing vessel");
                if (!sails)
                    {
                        String[] Subtypes ={"jetski", "yacht", "cargo ship"};
                        boolean validSubtypes =
                                vehicle.get(5).get(1).equals("powered vessel") &&
                                        isEqual(vehicle, 5, 2, Subtypes);
                        boolean validManufacturer =
                                isEqual(vehicle, 3, 1, manufacturers);
                        String[] fuels = {"diesel", "petrol"};
                        boolean validFuels =
                                (vehicle.get(5).get(2).equals("yacht") &&
                                        isEqual(vehicle, 3, 2, fuels)) ||
                                        (vehicle.get(5).get(2)
                                                .equals("jetski") &&vehicle.get(3).get(2).equals("petrol")) ||
                                        (vehicle.get(5).get(2)
                                                .equals("cargo ship") &&
                                                vehicle.get(3).get(2)
                                                        .equals("diesel"));
                        validSails = validSubtypes && validManufacturer &&
                                validFuels;
                    } else
                    {
                        String[] Subtypes ={"xebec", "frigate", "schooner"};
                        boolean validSubtypes =
                                vehicle.get(5).get(1).equals("sailing vessel") &&
                                        isEqual(vehicle, 5, 2, Subtypes);
                        boolean validManufacturer =
                                isEqual(vehicle, 4, 1, manufacturers);
                        String [] sailTypes = {"canvas", "nylon", "mylar"};
                        boolean validSailTypes = isEqual(vehicle, 4, 2,
                                sailTypes);
                        boolean sailNumberCheck =
                                !vehicle.get(4).get(3).chars().allMatch(Character::isAlphabetic);
                        boolean rightNumberSails =
                                (vehicle.get(5).get(2).equals(
                                "frigate") && vehicle.get(4).get(3).equals(
                                        "10")) || (vehicle.get(5).get(2).equals("xebec")
                                && vehicle.get(4).get(3).equals("3")) ||
                                (vehicle.get(5).get(2).equals("schooner") && vehicle.get(4).get(3).equals("6"));
                        validSails =
                                validSailTypes && validSubtypes && sailNumberCheck && rightNumberSails && validManufacturer;

                    }

                boolean valid = validColours && validManufacturers && validSails;

                if (sails && vehicle.get(5).get(2).equals("schooner") && vehicle.get(1).get(1).equals("black")) valid = false;
                if (sails &&vehicle.get(5).get(2).equals("xebec")&&  !vehicle.get(3).get(1).equals("Goliath Inc.")) valid = false;
                if (!sails && vehicle.get(5).get(2).equals("jetski") && !vehicle.get(1).get(1).equals("green")) valid = false;
                if (!sails && vehicle.get(5).get(2).equals("cargo ship") && !vehicle.get(4).get(1).equals("Goliath Inc.")) valid = false;

                return valid;
            }

        public static boolean isEqual(ArrayList<ArrayList<String>> vehicle,
                                      int i, int j, String[] options)
            {
                for (String option : options)
                    {
                        if (vehicle.get(i).get(j).equals(option)) return true;
                    }
                return false;
            }

        public static int costCalculator (ArrayList<ArrayList<String>> vehicle)
            {
                if (vehicle.get(vehicle.size()-1).get(0).equals(
                        "wheels"))
                {
                    int baseCost = 0;
                    int fuelCost = 0;
                    int wheelCost = 0;

                    if (vehicle.get(4).get(2).equals("hatchback"))
                        baseCost = 8000;
                    if (vehicle.get(4).get(2).equals("sedan")) baseCost = 12000;
                    if (vehicle.get(4).get(2).equals("convertible"))
                        baseCost = 20000;
                    if (vehicle.get(4).get(2).equals("pickup"))
                        baseCost = 20000;
                    if (vehicle.get(4).get(2).equals("eighteen wheeler"))
                        baseCost = 35000;
                    if (vehicle.get(4).get(2).equals("sport")) baseCost = 16000;
                    if (vehicle.get(4).get(2).equals("touring"))
                        baseCost = 9000;


                    if (vehicle.get(2).get(2).equals("electric"))
                        fuelCost = 5000;
                    if (vehicle.get(2).get(2).equals("petrol")) fuelCost = 1000;
                    if (vehicle.get(2).get(2).equals("diesel")) fuelCost = 2000;

                    if (vehicle.get(5).get(2).equals("summer")) wheelCost = 100;
                    if (vehicle.get(5).get(2).equals("winter")) wheelCost = 120;
                    if (vehicle.get(5).get(2).equals("all-weather"))
                        wheelCost = 150;

                    int totalCost =
                            baseCost + fuelCost + (wheelCost *
                                    Integer.parseInt(vehicle.get(5).get(3)));

                    if (vehicle.get(3).get(1).equals("ACME") &&
                            vehicle.get(5).get(1).equals("ACME") &&
                            vehicle.get(4).get(1).equals("car")) totalCost =
                            totalCost - 987;

                    if (vehicle.get(3).get(1).equals("Consolidated Products") &&
                            vehicle.get(5).get(2).equals("summer")) totalCost =
                            totalCost + 261;

                    if (vehicle.get(3).get(1).equals("Goliath Inc.") &&
                            vehicle.get(2).get(1).equals("Goliath Inc.") &&
                            vehicle.get(4).get(1).equals("car")) totalCost =
                            totalCost - 1354;

                    if (vehicle.get(2).get(2).equals("electric") &&
                            vehicle.get(4).get(1).equals("car")) totalCost =
                            totalCost - 974;

                    return totalCost;


                } else
                {
                    int baseCost = 0;
                    int fuelCost = 0;
                    int sailCost = 0;

                    if (vehicle.get(4).get(2).equals("frigate"))
                        baseCost = 100000;
                    if (vehicle.get(4).get(2).equals("xebec")) baseCost = 5000;
                    if (vehicle.get(4).get(2).equals("schooner"))
                        baseCost = 10000;
                    if (vehicle.get(4).get(2).equals("jetski"))
                        baseCost = 1000;
                    if (vehicle.get(4).get(2).equals("yacht"))
                        baseCost = 50000;
                    if (vehicle.get(4).get(2).equals("cargo ship")) baseCost =
                            100000;


                    boolean sails = vehicle.get(4).get(1).equals("sailing " +
                            "vessel");

                    if (!sails && vehicle.get(2).get(2).equals("petrol")) fuelCost = 1000;
                    if (!sails && vehicle.get(2).get(2).equals("diesel")) fuelCost = 2000;

                    if (sails && vehicle.get(3).get(2).equals("canvas")) sailCost = 500;
                    if (sails && vehicle.get(3).get(2).equals("nylon")) sailCost = 350;
                    if (sails && vehicle.get(3).get(2).equals("mylar"))
                        sailCost = 900;

                    int totalSailCost = 0;

                    if(sails) totalSailCost =sailCost *
                            Integer.parseInt(vehicle.get(3).get(3));

                    int totalCost =
                            baseCost + fuelCost + totalSailCost;

                    return totalCost;
                }
            }

    }


