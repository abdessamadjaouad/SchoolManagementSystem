package controllers;

import menu.Menu;
import classes.Departement;
import classes.Enseignant;
import classes.Filliere;
import services.DepartementService;
import services.EnseignantService;
import services.FilliereService;

import java.util.List;

public class FilliereController {
    public static void showMenu(){
        System.out.println("-------------------------Fillieres---------------------------");


        System.out.println("1: To add a Filliere");
        System.out.println("2: To display Fillieres");
        System.out.println("3: To modify a Filliere");
        System.out.println("4: To delete a Filliere");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createFilliere();
                break;
            case 2:
                showFillieres();
                break;
            case 3:
                editFilliere();
                break;
            case 4:
                destroyFilliere();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }
    public static void createFilliere() {
        Long id = (long) Menu.lireEntier("Enter Filliere ID: ");
        String intitule = Menu.lireString("Enter Filliere title:");

        DepartementController.showDepartements();
        Long idDepartement =(long) Menu.lireEntier("Please enter the ID of an existing department: ");

        DepartementService departementService = new DepartementService();
        Departement departementById = departementService.getDepartementById(idDepartement);

        EnseignantsController.showEnseignants();
        Long idEnseignent = (long)  Menu.lireEntier("Enter the ID of an existing enseignat:");
        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);


        Filliere filliere = new Filliere();
        filliere.setId(id);
        filliere.setIntitule(intitule);
        filliere.setDepartement(departementById);
        filliere.setEnseignant(enseignantById);

        FilliereService filliereService = new FilliereService();
        filliereService.ajouterFilliere(filliere);
    }
    public static void showFillieres() {
        FilliereService service = new FilliereService();
        List<Filliere> allFillieres = service.getAllFillieres();
        for (Filliere filliere:allFillieres){
            System.out.println(filliere.getId() + "|" + filliere.getIntitule());
        }
    }
    public static void editFilliere() {
        showFillieres();

        Long id = (long) Menu.lireEntier("Enter the ID of the Filliere you are looking for:");

        String intitule = Menu.lireString("Enter Filliere title:");

        DepartementController.showDepartements();
        Long idDepartement =(long) Menu.lireEntier("Please enter the ID of an existing department: ");

        DepartementService departementService = new DepartementService();
        Departement departementById = departementService.getDepartementById(idDepartement);

        EnseignantsController.showEnseignants();
        Long idEnseignent = (long)  Menu.lireEntier("Enter the ID of an existing enseignant:");
        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);


        FilliereService filliereService = new FilliereService();
        Filliere filliereById = filliereService.getFilliereById(id);

        filliereById.setIntitule(intitule);
        filliereById.setDepartement(departementById);
        filliereById.setEnseignant(enseignantById);

        filliereService.modifierFilliere(filliereById);

    }
    private static void destroyFilliere() {
        showFillieres();
        Long id =(long) Menu.lireEntier("Please enter the ID of the Filliere you wish to delete: ");

        FilliereService service = new FilliereService();
        service.supprimerFilliere(id);

    }
}
