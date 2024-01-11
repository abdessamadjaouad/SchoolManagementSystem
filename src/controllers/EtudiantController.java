package controllers;

import menu.Menu;
import classes.Etudiant;
import classes.Filliere;
import services.EtudiantService;
import services.FilliereService;

import java.util.List;

public class EtudiantController {
    public static void showMenu(){
        System.out.println("-------------------------Students---------------------------");


        System.out.println("1: To add a student");
        System.out.println("2: To display students");
        System.out.println("3: To modify a student");
        System.out.println("4: To delete a student");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createEtudiant();
                break;
            case 2:
                showEtudiants();
                break;
            case 3:
                editEtudiant();
                break;
            case 4:
                destroyEtudiant();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }
    public static void createEtudiant() {
        Long id = (long) Menu.lireEntier("Enter student ID:");
        int apogee = Menu.lireEntier("Enter student apogee:");

        String nom = Menu.lireString("Enter student last name:");
        String prenom = Menu.lireString("Enter student first name");
        String email = Menu.lireString("Enter student email:");
        FilliereController.showFillieres();

        Long idFilliere = (long) Menu.lireEntier("Enter the ID of the filiere you are looking for:");
        FilliereService filliereService = new FilliereService();
        Filliere filliereById = filliereService.getFilliereById(idFilliere);

        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setApogee(apogee);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setFilliere(filliereById);

        EtudiantService service = new EtudiantService();
        service.ajouterEtudiant(etudiant);

    }
    public static void showEtudiants() {
        EtudiantService service = new EtudiantService();
        List<Etudiant> allEtudiants = service.getAllEtudiants();
        for (Etudiant etudiant:allEtudiants){
            System.out.println(etudiant.getId() + "|" + etudiant.getApogee() + "|" + etudiant.getPrenom() + "|" + etudiant.getNom() + "|" + etudiant.getEmail());
        }
    }
    public static void editEtudiant() {
        showEtudiants();
        Long id = (long) Menu.lireEntier("Enter the ID of the student you are looking for:");
        int apogee = Menu.lireEntier("Enter student apogee:");

        String nom = Menu.lireString("Enter student last name:");
        String prenom = Menu.lireString("Enter student first name:");
        String email = Menu.lireString("Enter student email:");
        FilliereController.showFillieres();

        Long idFilliere = (long) Menu.lireEntier("Enter the ID of an existing filliere:");
        FilliereService filliereService = new FilliereService();
        Filliere filliereById = filliereService.getFilliereById(idFilliere);


        EtudiantService service = new EtudiantService();
        Etudiant etudiantById = service.getEtudiantById(id);
        etudiantById.setApogee(apogee);
        etudiantById.setNom(nom);
        etudiantById.setPrenom(prenom);
        etudiantById.setEmail(email);
        etudiantById.setFilliere(filliereById);

        service.modifierEtudiant(etudiantById);


    }
    public static void destroyEtudiant() {
        showEtudiants();
        EtudiantService service = new EtudiantService();
        Long id =(long) Menu.lireEntier("Please enter the ID of the student you wish to delete: ");
        service.supprimerEtudiant(id);

    }
}
