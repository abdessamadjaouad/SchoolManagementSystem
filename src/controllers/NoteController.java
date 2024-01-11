package controllers;

import menu.Menu;
import classes.Etudiant;
import classes.Module;
import classes.Note;
import services.EtudiantService;
import services.ModuleService;
import services.NoteService;

import java.util.List;

public class NoteController {
    public static void showMenu(){
        System.out.println("-------------------------Note---------------------------");


        System.out.println("1: To add a note");
        System.out.println("2: To display note");
        System.out.println("3: To modify a note");
        System.out.println("4: To delete a note");
        System.out.println("0: To return to the main menu");

        //"Veuillez sélectionner une option : ")
        int option = Menu.lireEntier("Choose an option: ");
        switch(option) {
            case 1:
                createNote();
                break;
            case 2:
                showNotes();
                break;
            case 3:
                editNote();
                break;
            case 4:
                destroyNote();
                break;
            default:
                Menu.showPrincipalMenu();
        }
    }
    public static void createNote() {
        Long idNote = (long) Menu.lireEntier("Enter the note ID:");
        double noteEtudiant =   Menu.lireEntier("Enter the student's note:");


        ModuleController.showModules();
        long idModule = Menu.lireEntier("Enter an existing module ID:"); ;
        ModuleService moduleService = new ModuleService();
        Module moduleById = moduleService.getModuleById(idModule);


        EtudiantController.showEtudiants();
        long idEtudiant = Menu.lireEntier("Enter an existing student ID:"); ;
        EtudiantService filliereService = new EtudiantService();
        Etudiant etudiantById = filliereService.getEtudiantById(idEtudiant);


        Note note = new Note();
        note.setId(idNote);
        note.setNote(noteEtudiant);
        note.setModule(moduleById);
        note.setEtudiant(etudiantById);

        NoteService noteService = new NoteService();
        noteService.ajouterNote(note);

    }
    public static void showNotes() {
        NoteService service = new NoteService();
        List<Note> allNotes = service.getAllNotes();
        for (Note note:allNotes){
            System.out.println(note.getId() + "|" + note.getNote());
        }
    }
    public static void editNote() {
        showNotes();
        Long idNote = (long) Menu.lireEntier("Enter the ID of the note you are searching for:");
        double noteEtudiant =   Menu.lireEntier("Enter the student's note:");


        ModuleController.showModules();
        long idModule = Menu.lireEntier("Enter an existing module ID:"); ;
        ModuleService moduleService = new ModuleService();
        Module moduleById = moduleService.getModuleById(idModule);


        EtudiantController.showEtudiants();
        long idEtudiant = Menu.lireEntier("Enter an existing student ID:"); ;
        EtudiantService filliereService = new EtudiantService();
        Etudiant etudiantById = filliereService.getEtudiantById(idEtudiant);


        NoteService noteService = new NoteService();
        Note noteById = noteService.getNoteById(idNote);

        noteById.setNote(noteEtudiant);
        noteById.setModule(moduleById);
        noteById.setEtudiant(etudiantById);

        noteService.modifierNote(noteById);


    }
    public static void destroyNote() {
        showNotes();
        Long id =(long) Menu.lireEntier("Please enter the ID of the note you want to delete: ");

        NoteService service = new NoteService();
        service.supprimerNote(id);

    }
}
