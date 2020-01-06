import Controller.Controller;
import Domain.*;
import Exceptions.*;
import Files.TextFileRepo;
import Repos.*;
import UI.UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        Controller ctrl = new Controller(ar, dr, fr, rr, tr);

        UI ui = new UI(ctrl);

        textFileRepo.populateRepos(ar,dr,fr,rr,tr);

        ui.start();

        textFileRepo.clearFile();

        textFileRepo.populateFile(ar, dr, fr, rr, tr);

    }
}
