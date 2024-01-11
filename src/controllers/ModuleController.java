package controllers;

import menu.Menu;
import classes.Enseignant;
import classes.Filliere;
import classes.Module;
import services.EnseignantService;
import services.FilliereService;
import services.ModuleService;

import java.util.List;

public class ModuleController {
    public static void showMenu(){
        System.out.println("-------------------------Module---------------------------");


        System.out.println("1: To add a Module");
        System.out.println("2: To display Modules");
        System.out.println("3: To modify a Module");
        System.out.println("4: To delete a Module");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createModule();
                break;
            case 2:
                showModules();
                break;
            case 3:
                editModule();
                break;
            case 4:
                destroyModule();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }

    public static void createModule() {
        Long idModule =(long) Menu.lireEntier("Enter the Module ID: ");
        String nom = Menu.lireString("Enter the module name:");


        FilliereController.showFillieres();
        Long idFilliere = (long) Menu.lireEntier("Enter the ID of the filliere you are searching for:");
        FilliereService filliereService = new FilliereService();
        Filliere filliereById = filliereService.getFilliereById(idFilliere);

        EnseignantsController.showEnseignants();
        Long idEnseignent = (long)  Menu.lireEntier("Enter the ID of the enseignant you are searching for:");
        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);


        Module module = new Module();
        module.setId(idModule);
        module.setNom(nom);
        module.setProfesseur(enseignantById);
        module.setModuleFiliere(filliereById);

        ModuleService moduleService = new ModuleService();
        moduleService.ajouterModule(module);
    }
    public static void showModules() {
        ModuleService service = new ModuleService();
        List<Module> allModules = service.getAllModules();
        for (Module module:allModules){
            System.out.println(module.getId() +" | "+module.getNom());
        }
    }
    public static void editModule() {

        showModules();
        Long idModule =(long) Menu.lireEntier("Enter the ID of the Module you are searching for: ");
        String nom = Menu.lireString("Enter the module name:");


        FilliereController.showFillieres();
        Long idFilliere = (long) Menu.lireEntier("Enter the ID of an existing filliere:");
        FilliereService filliereService = new FilliereService();
        Filliere filliereById = filliereService.getFilliereById(idFilliere);

        EnseignantsController.showEnseignants();
        Long idEnseignent = (long)  Menu.lireEntier("Enter the ID of an existing enseignant:");
        EnseignantService enseignantService = new EnseignantService();
        Enseignant enseignantById = enseignantService.getEnseignantById(idEnseignent);

        ModuleService moduleService = new ModuleService();
        Module moduleById = moduleService.getModuleById(idModule);
        moduleById.setNom(nom);
        moduleById.setProfesseur(enseignantById);
        moduleById.setModuleFiliere(filliereById);

        moduleService.modifierModule(moduleById);



    }

    private static void destroyModule() {
        showModules();
        Long id =(long) Menu.lireEntier("Please enter the ID of the Module you wish to delete: ");
        ModuleService service = new ModuleService();
        service.supprimerModule(id);

    }






}
