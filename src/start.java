import Controller.Controller;
import Domain.*;
import Exceptions.*;
import Repos.*;
import UI.UI;

import java.util.ArrayList;

public class start {
    public static void main(String[] args) throws
            ActivityException,
            TeacherException,
            DisciplineException,
            FormationException,
            RoomException, DisciplineRepoException, ActivityRepoException, FormationRepoException, TeacherRepoException, RoomRepoException {

        Teacher teach1 = new Teacher(1, "Pop", "Andrei", "dr", "popandrei@cs.ubbcluj.ro");
        Teacher teach2 = new Teacher(2, "Bere", "Darius", "prof", "darius.bogdan3080@gmail.com");
        Teacher teach3 = new Teacher(3, "Matthew", "Smith", "prof", "matthew@smith.com");
        Teacher teach4 = new Teacher(4, "Brian", "Griffin", "dr", "brian@griffin.ro");
        Teacher teach5 = new Teacher(5, "Andrew", "Simpson", "prof", "simpson@simpson.ro");

        Discipline disc1 = new Discipline("Complex Informatics", "Computer Science");
        Discipline disc2 = new Discipline("Complex Analysis", "Mathematics");
        Discipline disc3 = new Discipline("MAP", "Computer Science");
        Discipline disc4 = new Discipline("Web Development", "Computer Science");
        Discipline disc5 = new Discipline("Differential Equations", "Mathematics");

        Room room1 = new Room("C101", 100, "FSEGA");
        Room room2 = new Room("6/II", 100, "Centru");
        Room room3 = new Room("A320", 100, "Avram Iancu");
        Room room4 = new Room("A311", 100, "Avram Iancu");
        Room room5 = new Room("C531", 100, "FSEGA");

        Activity act1 = new Activity(disc2, "laboratory", teach2, room4);
        Activity act2 = new Activity(disc4, "lab", teach3, room1);
        Activity act3 = new Activity(disc3, "laboratory", teach2, room5);
        Activity act4 = new Activity(disc2, "lab", teach1, room3);
        Activity act5 = new Activity(disc2, "seminar", teach5, room2);
        Activity act6 = new Activity(disc1, "lecture", teach3, room4);
        Activity act7 = new Activity(disc5, "seminar", teach1, room1);
        Activity act8 = new Activity(disc1, "lab", teach5, room3);
        Activity act9 = new Activity(disc4, "laboratory", teach2, room4);
        Activity act10 = new Activity(disc3, "lecture", teach5, room2);
        Activity act11 = new Activity(disc1, "seminar", teach4, room3);
        Activity act12 = new Activity(disc5, "laboratory", teach2, room5);
        Activity act13 = new Activity(disc2, "lecture", teach1, room1);
        Activity act14 = new Activity(disc2, "seminar", teach2, room4);
        Activity act15 = new Activity(disc4, "lab", teach4, room3);

        ArrayList<Activity> activities1 = new ArrayList<>();
        ArrayList<Activity> activities2 = new ArrayList<>();
        ArrayList<Activity> activities3 = new ArrayList<>();
        ArrayList<Activity> activities4 = new ArrayList<>();
        ArrayList<Activity> activities5 = new ArrayList<>();

        activities1.add(act10);
        activities1.add(act5);
        activities1.add(act2);
        activities2.add(act3);
        activities2.add(act1);
        activities2.add(act6);
        activities3.add(act4);
        activities3.add(act7);
        activities3.add(act9);
        activities4.add(act11);
        activities4.add(act15);
        activities4.add(act13);
        activities5.add(act12);
        activities5.add(act2);
        activities5.add(act8);

        Formation form1 = new Formation("821", activities1);
        Formation form2 = new Formation("812", activities2);
        Formation form3 = new Formation("811", activities3);
        Formation form4 = new Formation("822", activities4);
        Formation form5 = new Formation("810", activities5);

        TeacherRepo tr = new TeacherRepo();
        DisciplineRepo dr = new DisciplineRepo();
        FormationRepo fr = new FormationRepo();
        RoomRepo rr = new RoomRepo();
        ActivityRepo ar = new ActivityRepo();

        ar.add(act1);
        ar.add(act2);
        ar.add(act3);
        ar.add(act4);
        ar.add(act5);
        ar.add(act6);
        ar.add(act7);
        ar.add(act8);
        ar.add(act9);
        ar.add(act10);
        ar.add(act11);
        ar.add(act12);
        ar.add(act13);
        ar.add(act14);
        ar.add(act15);

        tr.add(teach1);
        tr.add(teach2);
        tr.add(teach3);
        tr.add(teach4);
        tr.add(teach5);

        dr.add(disc1);
        dr.add(disc2);
        dr.add(disc3);
        dr.add(disc4);
        dr.add(disc5);

        rr.add(room1);
        rr.add(room2);
        rr.add(room3);
        rr.add(room4);
        rr.add(room5);

        fr.add(form1);
        fr.add(form2);
        fr.add(form3);
        fr.add(form4);
        fr.add(form5);

        Controller ctrl = new Controller(ar, dr, fr, rr, tr);

        UI ui = new UI(ctrl);

        ui.start();

    }
}
