import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EVEMain {
        public static void main(String[] args)
            {

                Scanner keyboard = new Scanner(System.in);


                {
                    //makes list of vehicles ot order them by id
                    ArrayList<ArrayList<ArrayList<String>>> vehicles = new ArrayList<>();

                    for (int i = 0; i < 2; i++)
                        {
                            ArrayList<ArrayList<String>> vehicle =
                                    CorrectInput(keyboard);
                            if(vehicles.isEmpty()) vehicles.add(vehicle);
                            else for (int k = 0; k < vehicles.size(); k++)
                                {
                                    if (Integer.parseInt(vehicles.get(k).getFirst().get(1))>Integer.parseInt(vehicle.getFirst().get(1)))
                                        {
                                            vehicles.add(k, vehicle);
                                            break;
                                        }

                                    else if (k == vehicles.size()-1 && Integer.parseInt(vehicles.get(k).getFirst().get(1))<=Integer.parseInt(vehicle.getFirst().get(1)))
                                        {
                                            vehicles.add(vehicle);
                                        }
                                }
                        }

                    for (ArrayList<ArrayList<String>> arrayLists : vehicles)
                        {
                            PrettyPrint(arrayLists);
                            System.out.println();
                        }
                }
            }

        public static ArrayList<ArrayList<String>> CorrectInput (Scanner keyboard)
            {
                if (keyboard.hasNext())
                    {
                        //makes a list of the details of the vehicle
                        ArrayList<ArrayList<String>> vehicle =
                                new ArrayList<>();

                        //for each of the six categories it adds
                        // the key and the info to a list
                        for (int j = 0; j < 6; j++)
                            {
                                // creates a list with key and
                                // information
                                ArrayList<String> info =
                                        new ArrayList<>();

                                String key = keyboard.next();
                                String string = keyboard.nextLine();

                                String [] information =
                                        string.split(",");

                                for (String s : information)
                                    {
                                        info.add(s.trim());
                                    }

                                if (key.contains(":"))
                                    {
                                        key = key.substring(0,
                                                key.indexOf(":"));
                                    }

                                info.addFirst(key);

                                vehicle.add(info);
                            }

                        Sort(vehicle);

                        return vehicle;


                    }
                return null;
            }

        public static void PrettyPrint (ArrayList<ArrayList<String>> vehicle)
            {
                for (ArrayList<String> info : vehicle)
                    {
                        System.out.println();
                        System.out.print(info.getFirst().trim() + ": ");
                        for (int i = 1; i < info.size(); i++)
                            {
                                System.out.print(removeColon(removeZero(info.get(i).trim())).trim());
                                if(i != info.size() - 1) System.out.print(", ");
                            }
                    }
            }

        public static String removeZero(String str)
            {
                int i = 0;
                while (i < str.length() && str.charAt(i) == '0') i++;
                return str.substring(i);
            }

        public static String removeColon(String str)
            {
                int i = 0;
                while (i < str.length() && str.charAt(i) == ':') i++;
                return str.substring(i);
            }

        public static void Sort (ArrayList<ArrayList<String>> list)
            {
                ArrayList<String> id = list.getFirst();
                list.sort(Comparator.comparing(ArrayList::getFirst));
                list.remove(id);
                list.addFirst(id);
            }
    }


