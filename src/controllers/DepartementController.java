package controllers;

import menu.Menu;
import classes.Departement;
import classes.Enseignant;
import services.DepartementService;
import services.EnseignantService;

import java.util.List;

public class DepartementController {

    public static void showMenu(){
        System.out.println("-------------------------Departements---------------------------");


        System.out.println("1: To add a department");
        System.out.println("2: To display departments");
        System.out.println("3: To modify a department");
        System.out.println("4: To delete a department");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createDepartement();
                break;
            case 2:
                showDepartements();
                break;
            case 3:
                editDepartement();
                break;
            case 4:
                destroyDepartement();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }
    public static void showDepartements(){
        DepartementService service = new DepartementService();
        List<Departement> allDepartements = service.getAllDepartements();
        for (Departement departement:allDepartements){
            System.out.println(departement.getId() +" | " +departement.getIntitule());
        }

    }
    public static void createDepartement(){
        Long id =(long) Menu.lireEntier("Please enter the department ID: ");
        String intitule = Menu.lireString("Enter the department title:");

        EnseignantsController.showEnseignants();
        long idEnseignent = Menu.lireEntier("Enter the ID of the teacher:");


        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);

        Departement departement = new Departement();
        departement.setId(id);
        departement.setIntitule(intitule);
        departement.setChef(enseignantById);


        DepartementService departementService = new DepartementService();
        departementService.ajouterDepartement(departement);

    }
    public static void editDepartement(){
        showDepartements();
        long id = Menu.lireEntier("Please enter the department ID: ");

        DepartementService service = new DepartementService();
        Departement departementById = service.getDepartementById(id);

        String intitule = Menu.lireString("Enter the department title:");
        EnseignantsController.showEnseignants();
        long idEnseignent = Menu.lireEntier("Enter the ID of an existing teacher:");
        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);

        departementById.setIntitule(intitule);
        departementById.setChef(enseignantById);

        DepartementService departementService = new DepartementService();
        departementService.modifierDepartement(departementById);
    }
    public static void destroyDepartement(){
        showDepartements();
        long id = Menu.lireEntier("Please enter the ID of the department you wish to delete: ");

        DepartementService departementService = new DepartementService();
        departementService.supprimerDepartement(id);

    }
}
