import java.util.Locale;
import java.util.Scanner;

import static tools.Common.printMenu;
import static tools.ToolsJson.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    static User user;
    static JSONObject userJson;

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
                "View the list of contact",
                "View the list of calls",
                "Call a contact",
                "Exit"
        };

        String[] hiddenOrNotMenu = {
                "THIS CONTACT IS HIDDEN",
                "Yes",
                "No",
        };

        boolean contPrincMenu;

        // Variable for login or register
        String username, password, passwordHiddenContact;
        JSONArray users;
        JSONObject tempUser = new JSONObject();
        boolean contToInsert;

        // Variable for creating a new Contact
        String name, surname, number;
        boolean hidden;
        JSONObject contactToInsert = new JSONObject();
        JSONArray arrContact;

        // Variable to delete a contact
        int indexOfContact = 0;
        boolean wantDelete;

        // Variable to set another password for the hidden contact
        String newPassWord;

        // Variable to view the list of contact
        String pwInsert, viewHiddenChoice;
        boolean viewHiddenChoiceBool;

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
                            System.out.println("ATTENTION: You have insert an invalid username");
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
                        } else if (!password.equals(tempUser.get("password"))) {
                            System.out.println("ATTENTION: You have insert an invalid password");
                            contToInsert = true;
                        }
                    } while (contToInsert);

                    if (!username.equals("q") || !password.equals("q")) {
                        // Load the user information using the constructor
                        user = new User((String) tempUser.get("username"), (String) tempUser.get("password"), (String) tempUser.get("pwHiddenContact"), getListContact(tempUser, "contact"), getListContact(tempUser, "calls"));
                        userJson = tempUser;
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
                    userJson = user.toJSONObj();

                    // update the json file of the users
                    users.add(userJson);

                    exit = false;
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } while (exit);

        do {
            contPrincMenu = true;

            switch (printMenu(principalMenu)) {
                // Insert new contact in your address book
                case 1: {
                    arrContact = (JSONArray) userJson.get("contact");
                    contactToInsert = new JSONObject();

                    // Get the name of the contact
                    do {
                        System.out.print("Insert the name of the contact which you want to insert: ");
                        name = scanner.next();

                        if (name.isEmpty())
                            System.out.println("ATTENTION: You have to insert a blank string");
                    } while (name.isEmpty());

                    // Get the surname of the contact
                    do {
                        System.out.print("Insert the surname of the contact which you want to insert: ");
                        surname = scanner.next();

                        if (surname.isEmpty())
                            System.out.println("ATTENTION: You have to insert a blank string");
                    } while (surname.isEmpty());

                    // Get the number of the contact
                    do {
                        System.out.print("Insert the number of the contact which you want to insert: ");
                        number = scanner.next();

                        if (number.isEmpty()) {
                            System.out.println("ATTENTION: You have to insert a blank string");
                        } else if (!checkIfDigit(number)) {
                            System.out.println("ATTENTION: You have to insert only number");
                        }
                    } while (number.isEmpty() || !checkIfDigit(number));

                    // Ask the user if the contact have to be hidden or no
                    hidden = printMenu(hiddenOrNotMenu) == 1;

                    // put all values in a JSONObj
                    contactToInsert.put("name", name);
                    contactToInsert.put("surname", surname);
                    contactToInsert.put("number", number);
                    contactToInsert.put("hidden", hidden);

                    // Put JSONObj in the array of contact
                    arrContact.add(contactToInsert);

                    break;
                }
                // Delete a contact in your address book
                case 2: {
                    wantDelete = true;

                    arrContact = (JSONArray) userJson.get("contact");

                    // If the list is empty then
                    // tell it to the user and break
                    if (arrContact.isEmpty()) {
                        System.out.println("The list of contact is empty.");
                        break;
                    }

                    // Ask the user if he wants to view also the hidden contact
                    do {
                        System.out.println("You want delete a hidden contact (y / n): ");
                        viewHiddenChoice = scanner.next();

                        if (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n")) {
                            System.out.println("ATTENTION: You have to insert y or n.");
                        }
                    } while (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n"));

                    viewHiddenChoiceBool = viewHiddenChoice.equalsIgnoreCase("y");

                    // If the user wants to delete a hidden contact
                    // ask him the password for the hidden contact
                    if (viewHiddenChoiceBool) {
                        do {
                            contToInsert = false;

                            System.out.print("Insert the password for the hidden contact (q to exit): ");
                            pwInsert = scanner.next();

                            if (pwInsert.equalsIgnoreCase("q")) {
                                wantDelete = false;
                                break;
                            // Check if the password insert is different from the user password
                            } else if (!pwInsert.equals(user.getPasswordHiddenContact())) {
                                System.out.println("ATTENTION: The insert password and your current password doesn't match");
                                contToInsert = true;
                            }
                        } while (contToInsert);

                        if (!pwInsert.equalsIgnoreCase("q")) {
                            indexOfContact = selectContact(arrContact, viewHiddenChoiceBool);
                        }
                    } else {
                        indexOfContact = selectContact(arrContact, viewHiddenChoiceBool);
                    }

                    // If the user didn't insert 0
                    if (indexOfContact != 0) {
                        arrContact.remove(indexOfContact - 1);
                    }

                    break;
                }
                // Insert a new password for view the hidden contact
                case 3: {
                    System.out.print("Insert the new password for the hidden contact");
                    newPassWord = scanner.next();

                    user.setPassword(newPassWord);
                    userJson.put("pwHiddenContact", newPassWord);
                    break;
                }
                // View contact
                case 4: {
                    if (((JSONArray) userJson.get("contact")).isEmpty()) {
                        System.out.println("ATTENTION: The list of contact is empty.");
                        break;
                    }

                    do {
                        System.out.println("You want view also the hidden contact (y / n): ");
                        viewHiddenChoice = scanner.next();

                        if (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n")) {
                            System.out.println("ATTENTION: You have to insert y or n.");
                        }
                    } while (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n"));

                    viewHiddenChoiceBool = viewHiddenChoice.equalsIgnoreCase("y");

                    if (viewHiddenChoiceBool) {
                        do {
                            contToInsert = false;

                            System.out.print("Insert the password for the hidden contact (q to exit): ");
                            pwInsert = scanner.next();

                            if (pwInsert.equalsIgnoreCase("q")) {
                                break;
                            } else if (!pwInsert.equals(user.getPasswordHiddenContact())) {
                                System.out.println("ATTENTION: The insert password and your current password doesn't match");
                                contToInsert = true;
                            }
                        } while (contToInsert);

                        if (!pwInsert.equalsIgnoreCase("q")) {
                            viewContact((JSONArray) userJson.get("contact"), viewHiddenChoiceBool);
                        }
                    } else {
                        viewContact((JSONArray) userJson.get("contact"), viewHiddenChoiceBool);
                    }

                    break;
                }
                // View the list of calls
                case 5: {
                    if (((JSONArray) userJson.get("calls")).isEmpty()) {
                        System.out.println("ATTENTION: The list of contact is empty.");
                        break;
                    }

                    do {
                        System.out.println("You want view also the calls to hidden contact (y / n): ");
                        viewHiddenChoice = scanner.next();

                        if (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n")) {
                            System.out.println("ATTENTION: You have to insert y or n.");
                        }
                    } while (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n"));

                    viewHiddenChoiceBool = viewHiddenChoice.equalsIgnoreCase("y");

                    if (viewHiddenChoiceBool) {
                        do {
                            contToInsert = false;

                            System.out.print("Insert the password for the hidden contact (q to exit): ");
                            pwInsert = scanner.next();

                            if (pwInsert.equalsIgnoreCase("q")) {
                                break;
                            } else if (!pwInsert.equals(user.getPasswordHiddenContact())) {
                                System.out.println("ATTENTION: The insert password and your current password doesn't match");
                                contToInsert = true;
                            }
                        } while (contToInsert);

                        if (!pwInsert.equalsIgnoreCase("q")) {
                            viewContact((JSONArray) userJson.get("calls"), viewHiddenChoiceBool);
                        }
                    } else {
                        viewContact((JSONArray) userJson.get("calls"), viewHiddenChoiceBool);
                    }
                    break;
                }
                // Call a contact
                case 6: {
                    if (((JSONArray) userJson.get("contact")).isEmpty()) {
                        System.out.println("ATTENTION: The list of contact is empty.");
                        break;
                    }

                    do {
                        System.out.println("You want call a hidden contact (y / n): ");
                        viewHiddenChoice = scanner.next();

                        if (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n")) {
                            System.out.println("ATTENTION: You have to insert y or n.");
                        }
                    } while (!viewHiddenChoice.equalsIgnoreCase("y") && !viewHiddenChoice.equalsIgnoreCase("n"));

                    viewHiddenChoiceBool = viewHiddenChoice.equalsIgnoreCase("y");

                    if (viewHiddenChoiceBool) {
                        do {
                            contToInsert = false;

                            System.out.print("Insert the password for the hidden contact (q to exit): ");
                            pwInsert = scanner.next();

                            if (pwInsert.equalsIgnoreCase("q")) {
                                break;
                            } else if (!pwInsert.equals(user.getPasswordHiddenContact())) {
                                System.out.println("ATTENTION: The insert password and your current password doesn't match");
                                contToInsert = true;
                            }
                        } while (contToInsert);

                        if (!pwInsert.equalsIgnoreCase("q")) {
                            indexOfContact = selectContact((JSONArray) userJson.get("contact"), viewHiddenChoiceBool);
                        }
                    } else {
                        indexOfContact = selectContact((JSONArray) userJson.get("contact"), viewHiddenChoiceBool);
                    }

                    arrContact = (JSONArray) userJson.get("calls");

                    arrContact.add(((JSONArray) userJson.get("contact")).get(indexOfContact-1));
                    break;
                }
                // Exit
                default: {
                    contPrincMenu = false;
                    break;
                }
            }
        } while (contPrincMenu);

        // Write all the changes to the Users.json file
        writeJSONArr("src/JSON/Users.json", users);
    }

    private static boolean checkIfDigit(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void viewContact(JSONArray array, boolean hidden) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject contact = (JSONObject) array.get(i);
            if (hidden) {
                System.out.printf("Name: %s, Surname: %s, Number: %s, Hidden: %s\n", contact.get("name"), contact.get("surname"), contact.get("number"), contact.get("hidden"));
            } else if ((boolean) contact.get("hidden") == hidden) {
                System.out.printf("Name: %s, Surname: %s, Number: %s, Hidden: %s\n", contact.get("name"), contact.get("surname"), contact.get("number"), contact.get("hidden"));
            }

        }
    }

    private static int selectContact(JSONArray contacts, boolean hidden) {
        boolean contToInsert;
        int indexOfContact = 0;

        for (int i = 0; i < contacts.size(); i++) {
            JSONObject contact = (JSONObject) contacts.get(i);

            if (hidden) {
                System.out.printf("[" + (i + 1) + "] - Name: %s, Surname: %s, Number: %s, Hidden: %s\n", contact.get("name"), contact.get("surname"), contact.get("number"), contact.get("hidden"));
            } else if ((boolean) contact.get("hidden") == hidden) {
                System.out.printf("[" + (i + 1) + "] - Name: %s, Surname: %s, Number: %s, Hidden: %s\n", contact.get("name"), contact.get("surname"), contact.get("number"), contact.get("hidden"));
            }
        }

        do {
            contToInsert = false;

            try {
                System.out.print("Insert the index of the contact which you want to delete (0 to exit): ");
                indexOfContact = Integer.parseInt(scanner.next());

                // Check if the insertion is between 1 and the size of the arr
                if (indexOfContact < 0 || indexOfContact > contacts.size()) {
                    System.out.println("ATTENTION: You have to insert a number between 1 and " + contacts.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("ATTENTION: You have to insert a number.");
                contToInsert = true;
            }
        } while (contToInsert);

        return indexOfContact;
    }

    private static JSONObject getUserFromUsername(String username, JSONArray users) {
        JSONObject userJsonObj;
        for (Object userObj : users) {
            userJsonObj = (JSONObject) userObj;

            if (userJsonObj.get("username").equals(username)) {
                return userJsonObj;
            }
        }

        return null;
    }

    private static Contact[] getListContact(JSONObject jsonObject, String paramName) {
        JSONArray arrayOfContact = (JSONArray) jsonObject.get(paramName);
        Contact[] list = new Contact[arrayOfContact.size()];

        for (int i = 0; i < list.length; i++) {
            JSONObject userJsonObj = (JSONObject) arrayOfContact.get(i);

            String name = (String) userJsonObj.get("name");
            String surname = (String) userJsonObj.get("surname");
            String number = (String) userJsonObj.get("number");
            boolean hidden = (boolean) userJsonObj.get("hidden");

            list[i] = new Contact(name, surname, number, hidden);
        }

        return list;
    }
}
