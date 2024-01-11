package menu;

import controllers.*;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static int lireEntier(String s) {
        System.out.println(s);
        int entier = 0;
        boolean entierValide = false;

        while (!entierValide) {
            try {
                entier = Integer.parseInt(scanner.nextLine());
                entierValide = true;
                System.out.println(entier);
            } catch (NumberFormatException e) {
                System.out.println("Insert a valid number !");
            }
        }

        return entier;
    }
    public static String lireString(String s) {
        System.out.println(s);
        String message = "";
        boolean saisieValide = false;

        while (!saisieValide) {
            message = scanner.nextLine().trim();
            if (message != null && !message.isEmpty()) {
                saisieValide = true;
            } else {
                System.out.println("Enter a valid string !");
            }
        }
        return message;
    }
    public static void showPrincipalMenu(){
        System.out.println("-------------------------Main Menu---------------------------");

        System.out.println("1: To manage the departments");
        System.out.println("2: To manage the filliere");
        System.out.println("3: To manage the enseignant");
        System.out.println("4: To manage the modules");
        System.out.println("5: To manage the students");
        System.out.println("6: To manage the note");
        System.out.println("0: To exit");

        int option = lireEntier("Choose an option : ");
        switch(option) {
            case 1:
                DepartementController.showMenu();
                break;
            case 2:
                FilliereController.showMenu();
                break;
            case 3:
                EnseignantsController.showMenu();
                break;
            case 4:
                ModuleController.showMenu();
                break;
            case 5:
                EtudiantController.showMenu();
                break;
            case 6:
                NoteController.showMenu();
                break;
            default:
        }
    }

}

