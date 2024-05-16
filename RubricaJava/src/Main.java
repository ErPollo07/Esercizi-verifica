import java.util.Scanner;

import static tools.Common.printMenu;

import org.json.simple.JSONObject;


public class Main {
    static Scanner scanner = new Scanner(System.in);

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

        // far visualizzare un menu per scegliere se fare il login e registrarsi

        // Se sceglie di loggarsi allora:
            // Chiedere all'utente username e password e password per i contatti nascosti
            // se o l'username o password non esistono e far reinserire
        // Se sceglie di registrarsi allora:
            // Far inserire username e password, controllare username se esiste già e la password se è vuota
            // Far fare il login

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
}
