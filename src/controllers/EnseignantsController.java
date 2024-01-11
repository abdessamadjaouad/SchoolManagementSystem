package controllers;

import menu.Menu;
import classes.Enseignant;
import services.EnseignantService;

import java.util.List;

public class EnseignantsController {
    public static void showMenu(){
        System.out.println("-------------------------Enseignants---------------------------");


        System.out.println("1: To add a teacher");
        System.out.println("2: To display teachers");
        System.out.println("3: To modify a teacher");
        System.out.println("4: To delete a teacher");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createEnseignat();
                break;
            case 2:
                showEnseignants();
                break;
            case 3:
                editEnseignant();
                break;
            case 4:
                destroyEnseignants();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }
    public static void createEnseignat() {
        Long id = (long) Menu.lireEntier("Enter Enseignant ID:");
        String nom = Menu.lireString("Enter enseignant last name:");
        String prenom = Menu.lireString("Enter Enseignant first name:");
        String email = Menu.lireString("Enter Enseignant email:");
        String gradle = Menu.lireString("Enter Enseignant grade:");

        Enseignant enseignant = new Enseignant();
        enseignant.setId(id);
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setGrade(gradle);

        EnseignantService service = new EnseignantService();
        service.ajouterEnseignant(enseignant);

    }
    public static void showEnseignants() {
        EnseignantService service = new EnseignantService();
        List<Enseignant> allEnseignants = service.getAllEnseignants();
        for (Enseignant enseignant:allEnseignants){
            System.out.println(enseignant.getId() + "|" + enseignant.getPrenom() + "|" + enseignant.getNom());
        }
    }
    public static void editEnseignant() {
        showEnseignants();
        Long id =(long) Menu.lireEntier("Please enter the ID of an existing Enseignant: ");

        EnseignantService service = new EnseignantService();
        Enseignant enseignantById = service.getEnseignantById(id);

        String nom = Menu.lireString("Enter enseignant last name:");
        String prenom = Menu.lireString("Enter enseignant first name:");
        String email = Menu.lireString("Enter enseignant email:");
        String gradle = Menu.lireString("Enter enseignant grade:");

        enseignantById.setNom(nom);
        enseignantById.setPrenom(prenom);
        enseignantById.setEmail(email);
        enseignantById.setGrade(gradle);

        service.modifierEnseignant(enseignantById);
    }
    public static void destroyEnseignants() {
        showEnseignants();
        EnseignantService service = new EnseignantService();
        Long id =(long) Menu.lireEntier("Please enter the ID of the enseignant you wish to delete:");
        service.supprimerEnseignant(id);

    }






}
