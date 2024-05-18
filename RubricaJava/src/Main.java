import java.util.Scanner;

import static tools.Common.printMenu;
import static tools.ToolsJson.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.tools.Tool;
import javax.xml.transform.Source;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    static User user;

    static boolean exit;

    public static void main(String[] args) {
        String[] logigOrSingInMenu = {
                "WELCOME",
                "Login",
                "Sing in",
                "Exit"
        };

        String[] principalMenu = {
                "PRINCIPAL MENU",
                "Insert new contact in your address book",
                "Delete a contact in your address book",
                "Insert a new password for view the hidden contact",
                "View visible contact",
                "View hidden contact",
                "View the list of calls to visible contact",
                "View the list of calls to hidden contact",
                "Exit"
        };

        // Variable for login or register
        String username, password, passwordHiddenContact;
        JSONArray users;
        JSONObject tempUser = new JSONObject();
        boolean contToInsert;

        // Read the Users file
        users = readJSONArr("src/JSON/Users.json");

        do {
            exit = true;

            switch (printMenu(logigOrSingInMenu)) {
                case 1:
                    // Insert Username
                    do {
                        contToInsert = false;

                        System.out.print("Insert your username (q to exit): ");
                        username = scanner.next();

                        if (username.equals("q")) {
                            break;
                        } else if ((tempUser = getUserFromUsername(username, users)) == null) {
                            System.out.println("You have insert an invalid username");
                            contToInsert = true;
                        }
                    } while (contToInsert);

                    // Insert password
                    do {
                        contToInsert = false;

                        System.out.print("Insert your password (q to exit): ");
                        password = scanner.next();

                        if (password.equalsIgnoreCase("q")) {
                            break;
                        } else if (!password.equals((String) tempUser.get("password"))) {
                            System.out.println("You have insert an invalid password");
                            contToInsert = true;
                        }
                    } while (contToInsert);

                    if (!username.equals("q") || !password.equals("q")) {
                        // Load the user information using the constructor
                        user = new User((String) tempUser.get("username"), (String) tempUser.get("password"), (String) tempUser.get("pwHiddenContact"), getListContact(tempUser, "visibleContact"), getListContact(tempUser, "nonVisibleContact"), getListContact(tempUser, "callsToVisibleContact"), getListContact(tempUser, "callsToNonVisibleContact"));
                        exit = false;
                    }

                    break;
                case 2:
                    System.out.print("Insert your username (q to exit): ");
                    username = scanner.next();

                    if (username.equals("q")) {
                        exit = true;
                        break;
                    }

                    System.out.print("Insert your password (q to exit): ");
                    password = scanner.next();

                    if (password.equals("q")) {
                        exit = true;
                        break;
                    }

                    System.out.print("Insert your password for the hidden contact (q to exit): ");
                    passwordHiddenContact = scanner.next();

                    if (passwordHiddenContact.equals("q")) {
                        exit = true;
                        break;
                    }

                    user = new User(username, password, passwordHiddenContact);

                    // update the json file of the users
                    users.add((Object) user.toJSONObj());

                    exit = false;
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } while (exit);

        System.out.println("Hai effetuato il login");

        // Write all the changes to the Users.json file
        writeJSONArr("src/JSON/Users.json", users);

        // far visualizzare il menu
        // 1. inserimento di un nuovo contatto nella rubrica;
        // 2. la cancellazione di un contatto dalla rubrica;
        // 3. inserimento di una password per i contatti nascosti;
        // 4. visualizzazione dei contatti visibili.
        // 5. visualizzazione dei contatti nascosti (prima di visualizzarli serve inserire la password);
        // 6. visualizza lista chimate contatti visibili;
        // 7. visualizza lista chiamate contatti nascosti;
        // 8. uscita.

        // METODI
        // createNewContact
        // deleteContact
        // setPwHidden
        // viewNonHiddenContact
        // viewHiddenContact
        // viewCallNonHiddenContact
        // viewCallHiddenContact
    }

    private static JSONObject getUserFromUsername(String username, JSONArray users) {
        JSONObject userJsonObj;
        String usernameOfObj = "";
        for (Object userObj : users) {
            userJsonObj = (JSONObject) userObj;

            if (((String) userJsonObj.get("username")).equals(username)) {
                return userJsonObj;
            }
        }

        return null;
    }

    private static Contact[] getListContact(JSONObject jsonObject, String paramName) {
        JSONArray arrayOfContact = (JSONArray)jsonObject.get(paramName);

        Contact[] list = new Contact[arrayOfContact.size()];

        for (int i = 0; i < list.length; i++) {
            JSONObject userJsonObj = (JSONObject) arrayOfContact.get(i);

            String name = (String) userJsonObj.get("name");
            String surname = (String) userJsonObj.get("surname");
            boolean hidden = (boolean) userJsonObj.get("hidden");

            list[i] = new Contact(name, surname, hidden);
        }

        return list;
    }
}
