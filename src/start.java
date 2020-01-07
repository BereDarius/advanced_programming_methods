import Controller.Controller;
import Domain.*;
import Exceptions.*;
import Files.TextFileRepo;
import Repos.*;
import UI.UI;

import java.io.IOException;

public class start {
    public static void main(String[] args) throws
            ActivityException,
            TeacherException,
            DisciplineException,
            FormationException,
            RoomException, DisciplineRepoException, ActivityRepoException, FormationRepoException, TeacherRepoException, RoomRepoException, IOException {

        String text_file ="Repo.txt";

        TextFileRepo textFileRepo = new TextFileRepo(text_file);

        TeacherRepo tr = new TeacherRepo();
        DisciplineRepo dr = new DisciplineRepo();
        FormationRepo fr = new FormationRepo();
        RoomRepo rr = new RoomRepo();
        ActivityRepo ar = new ActivityRepo();

        BinaryRepo<Teacher> binaryTeachers = new BinaryRepo<>("BinaryTeachers.ser");

        DBrepo database = new DBrepo();

        Controller ctrl = new Controller(ar, dr, fr, rr, tr, binaryTeachers, database);

        ctrl.openDBConnection();

        for (Activity a: ctrl.getAllActivities().getAll()) {
            ctrl.addActivityDB(a.getId(), a.getDiscipline(), a.getActivityType(), a.getTeacher(), a.getRoom());
        }

        for (Teacher t: ctrl.getAllTeachers().getAll()) {
            ctrl.addTeacherDB(t.getId(), t.getFirstName(), t.getLastName(), t.getTitle(), t.getEmail());
        }

        UI ui = new UI(ctrl);

        textFileRepo.populateRepos(ar,dr,fr,rr,tr);

        ui.start();

        ctrl.closeDBConnection();

        textFileRepo.clearFile();

        textFileRepo.populateFile(ar, dr, fr, rr, tr);

    }
}
