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

                                    for (ArrayList<String> strings : vehicle)
                                        {
                                            System.out.println(strings);
                                        }
                                }
                        }
                }
            }
    }


