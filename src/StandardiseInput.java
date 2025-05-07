import java.util.ArrayList;
import java.util.Scanner;

public class StandardiseInput
    {
        public static void main(String[] args)
            {

                Scanner keyboard = new Scanner(System.in);
                {
                    for (int r = 0; r < 10; r++)
                        {
                            if (keyboard.hasNext())
                                {
                                    ArrayList<ArrayList<String>> vehicle =
                                            new ArrayList<>();
                                    ArrayList<String> id = new ArrayList<>();
                                    String vehicleid = keyboard.next();
                                    if (vehicleid.contains(":"))
                                        {
                                            vehicleid = vehicleid.substring(0,
                                                    vehicleid.indexOf(":"));
                                        }
                                    id.add(vehicleid);
                                    id.add(Integer.toString(
                                            keyboard.nextInt()));
                                    vehicle.add(id);
                                    for (int j = 0; j < 5; j++)
                                        {
                                            ArrayList<String> info =
                                                    new ArrayList<>();
                                            String key = keyboard.next();
                                            if (key.contains(":"))
                                                {
                                                    key = key.substring(0,
                                                            key.indexOf(":"));
                                                }

                                            String string = keyboard.nextLine();

                                            info.add(key);
                                            info.add(string.substring(
                                                            string.indexOf(":") + 1)
                                                    .trim());
                                            vehicle.add(info);
                                        }

                                    PrettyPrint(vehicle);
                                }
                        }
                }
            }

        public static void PrettyPrint (ArrayList<ArrayList<String>> vehicle)
            {
                for (ArrayList<String> strings : vehicle)
                    {
                        System.out.println();
                        System.out.print(strings.get(0).trim() + ": ");
                        for (int i = 1; i < strings.size(); i++)
                            {
                                System.out.print(removeZero(strings.get(i).trim()));
                                if(i != strings.size() - 1) System.out.print(", ");
                            }
                    }
            }

        public static String removeZero(String str)
            {
                int i = 0;
                while (i < str.length() && str.charAt(i) == '0') i++;
                return str.substring(i);
            }
    }


