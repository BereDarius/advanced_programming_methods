package UI;

import Controller.Controller;
import Domain.*;
import Exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private Controller ctrl;

    public UI(Controller ctrl) {
        this.ctrl = ctrl;
    }

    private void readTeachersDB(){
        ArrayList<Teacher> teachers = this.ctrl.readDatabaseTeachers();
        for(Teacher t: teachers)
            System.out.println(t);
    }

    private void readActivitiesDB() {
        ArrayList<Activity> activities = this.ctrl.readDatabaseActivities();
        for(Activity a: activities)
            System.out.println(a);
    }

    private void serializeTeachers(){
        ArrayList<Teacher> teachers = this.ctrl.getAllTeachers().getAll();
        this.ctrl.serializeTeachers(teachers);
    }

    private void deserializeTeachers() throws IOException {
        ArrayList<Teacher> teachers = this.ctrl.deserializeTeacher();
        for(Teacher t: teachers){
            System.out.println(t);
            try {
                this.ctrl.addTeacher(t);
            }catch (TeacherRepoException | TeacherException ignored){

            }
        }
    }

    /*
    METHODS FOR PRINTING ACTIVITIES, DISCIPLINES, FORMATIONS, ROOMS AND TEACHERS BETTER
     */

    private String activityStringById(int id) {
        Activity activity = this.ctrl.getAllActivities().getActivityById(id);
        return "ID: " + activity.getId() +
                "\n\tDiscipline: " + disciplineStringById(activity.getDiscipline()) +
                "\tActivity type: " + activity.getActivityType() +
                "\n\tTeacher: " + teacherStringById(activity.getTeacher()) +
                "\n\tRoom: " + roomStringByNumber(activity.getRoom()) + "\n";
    }

    private String disciplineStringById(int id) {
        Discipline discipline = this.ctrl.getAllDisciplines().getDisciplineById(id);
        return "ID: " + discipline.getId() +
                "\n\tName: " + discipline.getName() +
                "\n\tField: " + discipline.getField() + "\n";
    }

    /*
    private String formationStringById(int id) {
        Formation formation = this.ctrl.getAllFormations().getFormationById(id);
        StringBuilder result = new StringBuilder("ID: " + formation.getId() +
                "\n\tName: " + formation.getName() + "\n\tSubgroup: ");
        if (formation.getSubgroup() != null) {
            result.append("Subgroup:\n\t" + formationStringById(formation.getSubgroup().getId())).append("\n\t");
        }
        result.append("Activities: \n\t");
        for (int activity_id : formation.getActivities()) {
            result.append(activityStringById(activity_id));
            result.append("\n\t");
        }
        return result.toString();
    }
     */

    private String roomStringByNumber(String num) {
        Room room = this.ctrl.getAllRooms().getRoomByRoomNumber(num);
        StringBuilder result = new StringBuilder(room.getRoomNumber() +
                "\n\tAvailable seats: " + room.getAvailableSeats() +
                "\n\tBuilding: " + room.getBuilding() +
                "\n\tActivity types: ");
        for (String s : room.getActivityTypes()) {
            result.append(s).append(" ,");
        }
        return result.toString();
    }

    private String teacherStringById(int id) {
        Teacher teacher = this.ctrl.getAllTeachers().getTeacherById(id);
        return "ID: " + teacher.getId() +
                "\n\tFirst name: " + teacher.getFirstName() +
                "\n\tLast name: " + teacher.getLastName() +
                "\n\tTitle: " + teacher.getTitle() +
                "\n\tEmail: " + teacher.getEmail();
    }

    /*
    METHOD THAT PRINTS A MESSAGE TO THE SCREEN, READS AN INTEGER INPUT AND RETURNS IT
     */

    private int readInt(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    /*
    METHOD THAT PRINTS A MESSAGE TO THE SCREEN, READS A STRING INPUT AND RETURNS IT
     */

    private String readString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
    METHOD THAT PRINTS THE MENU
     */

    private void printMenu() {
        System.out.println(
                "MENU:\n" +
                        "\t0. Exit;\n" +
                        "\t1. Activities;\n" +
                        "\t2. Disciplines;\n" +
                        "\t3. Formations;\n" +
                        "\t4. Rooms;\n" +
                        "\t5. Teachers;\n"
        );
    }

    /*
    METHOD THAT PRINTS THE MENU FOR ACTIVITIES
     */

    private void printMenuActivities() {
        System.out.println(
                "\tActivities:\n" +
                        "\t\t0. Back;\n" +
                        "\t\t1. Get all activities;\n" +
                        "\t\t2. Add activity to the activities repo;\n" +
                        "\t\t3. Insert an activity (at an index);\n" +
                        "\t\t4. Remove an activity from the repo (by index);\n" +
                        "\t\t5. Read activities from the database;\n"
        );
    }

    /*
    METHOD THAT PRINTS ALL ACTIVITIES FROM THE ACTIVITIES REPO TO THE SCREEN
     */

    private void printAllActivities() {
        for (int i = 0; i < this.ctrl.getAllActivities().getSize(); ++i) {
            System.out.println(("[" + i + "] " + activityStringById(ctrl.getAllActivities().getAll().get(i).getId())));
        }
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printAddActivity() {
        System.out.println("Activity added successfully.");
    }

    private Activity readActivity() throws ActivityException, DisciplineException, RoomException, TeacherException, DisciplineRepoException, TeacherRepoException, RoomRepoException, IOException {
        int id = readInt("Please enter the activity's ID: ");
        System.out.print("Would you like to choose a discipline from the repository for the activity?");
        String answer = readString("[Yes / No] ");
        int index;
        Discipline discipline;
        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
            System.out.print("Please select the index of the chosen discipline: ");
            printAllDisciplines();
            index = readInt("Choice: ");
            discipline = this.ctrl.getAllDisciplines().getAll().get(index);
        } else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
            discipline = readDiscipline();
            this.ctrl.addDiscipline(discipline);
        } else {
            return null;
        }
        String activity_type = readString("Please enter the activity type: ");
        Teacher teacher;
        System.out.print("Would you like to choose a teacher from the repository for the activity?");
        answer = readString("[Yes / No] ");
        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
            System.out.print("Please select the index of the chosen teacher: ");
            printAllTeachers();
            index = readInt("Choice: ");
            teacher = this.ctrl.getAllTeachers().getAll().get(index);
        } else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
            teacher = readTeacher();
            this.ctrl.addTeacher(teacher);
        } else {
            return null;
        }
        Room room;
        System.out.print("Would you like to choose a room from the repository for the activity?");
        answer = readString("[Yes / No] ");
        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
            System.out.print("Please select the index of the chosen room: ");
            printAllRooms();
            index = readInt("Choice: ");
            room = this.ctrl.getAllRooms().getAll().get(index);
        } else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
            room = readRoom();
            this.ctrl.addRoom(room);
        } else {
            return null;
        }
        return new Activity(id, discipline.getId(), activity_type, teacher.getId(), room.getRoomNumber());
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printInsertActivity() {
        System.out.println("Activity inserted successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printRemoveActivity() {
        System.out.println("Activity removed successfully.");
    }

    /*
    METHOD THAT PRINTS THE MENU FOR DISCIPLINES
     */

    private void printMenuDisciplines() {
        System.out.println(
                "\tDisciplines:\n" +
                        "\t\t0. Back;\n" +
                        "\t\t1. Get all disciplines;\n" +
                        "\t\t2. Add discipline to the repo;\n" +
                        "\t\t3. Remove a discipline from the repo (by index);\n" +
                        "\t\t4. Remove a discipline from the repo (by name);\n" +
                        "\t\t5. Update a discipline from the repo (by index);\n" +
                        "\t\t6. Update a discipline from the repo (by name);\n"
        );
    }

    /*
    METHOD THAT PRINTS ALL DISCIPLINES FROM THE DISCIPLINES REPO
     */

    private void printAllDisciplines() {
        for (int i = 0; i < this.ctrl.getAllDisciplines().getSize(); ++i) {
            System.out.println("[" + i + "] " + this.ctrl.getAllDisciplines().getAll().get(i));
        }
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printAddDiscipline() {
        System.out.println("Discipline added successfully.");
    }

    private Discipline readDiscipline() throws DisciplineException {
        int id = readInt("Please enter the discipline's ID: ");
        String name = readString("Please enter the disciplines's name: ");
        String field = readString("Please enter the disciplines's field: ");
        return new Discipline(id, name, field);
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printRemoveDiscipline() {
        System.out.println("Discipline removed successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printUpdateDiscipline() {
        System.out.println("Discipline updated successfully.");
    }

    /*
    METHOD THAT PRINTS THE MENU FOR FORMATIONS
     */

    private void printMenuFormations() {
        System.out.println(
                "\tFormations:\n" +
                    "\t\t0. Back;\n" +
                    "\t\t1. Get all formations;\n" +
                    "\t\t2. Add a formation to the repo;\n" +
                    "\t\t3. Update a formation from the repo (by index);\n" +
                    "\t\t4. Update a formation from the repo (by name);\n" +
                    "\t\t5. Remove a formation from the repo (by index);\n" +
                    "\t\t6. Remove a formation from the repo (by name);\n"
        );
    }

    /*
    METHOD THAT PRINTS ALL FORMATIONS FROM THE DISCIPLINES REPO
     */

    private void printAllFormations() {
        for (int i = 0; i < this.ctrl.getAllFormations().getSize(); ++i) {
            System.out.println("[" + i + "] " + this.ctrl.getAllFormations().getAll().get(i));
        }
    }

    private Formation readFormation() throws FormationException {
        int id = readInt("Please enter the formation's ID: ");
        String name = readString("Please enter the formation's name: ");
        ArrayList<Integer> activities = new ArrayList<>();
        System.out.print("Please choose an activity from the list below for the formation:\n");
        String answer;
        int index;
        while (true) {
            printAllActivities();
            index = readInt("Choice: ");
            if (index < 0 || index >= this.ctrl.getAllActivities().getSize() ||
                    activities.contains(this.ctrl.getAllActivities().getAll().get(index).getId())) {
                System.out.println("Activity already added or index out of bounds...");
                break;
            } else {
                activities.add(this.ctrl.getAllActivities().getAll().get(index).getId());
                System.out.println("Activity added!");
            }
            answer = readString("Do you wish to add another activity? [Yes / No] ");
            if (!answer.toLowerCase().equals("yes") && !answer.toLowerCase().equals("y")) {
                break;
            }
        }
        Formation subgroup = null;
        Formation resultFormation = new Formation(id, name, activities);
        answer = readString("Does this formation have a subgroup? [Yes / No] ");
        if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
            return resultFormation;
        } else if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
            answer = readString("Does this subgroup exist in the formations repo? [Yes / No] ");
            if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
                subgroup = readFormation();
            } else if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                printAllFormations();
                index = readInt("Please enter the index of the subgroup: ");
                subgroup = this.ctrl.getAllFormations().getAll().get(index);
            }
        }
        resultFormation.setSubgroup(subgroup);
        return resultFormation;
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printAddFormation() {
        System.out.println("Formation added successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printRemoveFormation() {
        System.out.println("Formation removed successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printUpdateFormation() {
        System.out.println("Formation updated successfully.");
    }

    /*
    METHOD THAT PRINTS THE MENU FOR ROOMS
     */

    private void printMenuRooms() {
        System.out.println(
                "\tRooms:\n" +
                        "\t\t0. Back;\n" +
                        "\t\t1. Get all rooms;\n" +
                        "\t\t2. Add a room to the repo;\n" +
                        "\t\t3. Remove a room from the repo (by index);\n" +
                        "\t\t4. Remove a room from the repo (by number);\n" +
                        "\t\t5. Update a room from the repo (by index);\n" +
                        "\t\t6. Update a room from the repo (by number);"
        );
    }

    /*
    METHOD THAT PRINTS ALL ROOMS FROM THE DISCIPLINES REPO
     */

    private void printAllRooms() {
        for (int i = 0; i < this.ctrl.getAllRooms().getSize(); ++i) {
            System.out.println("[" + i + "] " + this.ctrl.getAllRooms().getAll().get(i));
        }
    }

    private Room readRoom() throws RoomException {
        String number = readString("Please enter the room's number: ");
        int availableSeats = readInt("Please enter the room's number of available seats: ");
        String building = readString("Please enter the room's building: ");
        ArrayList<String> activities = new ArrayList<>();
        activities.add("lab");
        activities.add("laboratory");
        activities.add("seminar");
        activities.add("lecture");
        String activity_type;
        String answer;
        ArrayList<String> possible_activities = new ArrayList<>();
        do {
            for (String activity : activities) {
                System.out.println(activity + ";");
            }
            activity_type = readString("Please choose an activity type to add to the room: ");
            if (activities.contains(activity_type.toLowerCase())) {
                possible_activities.add(activity_type);
                if (activity_type.toLowerCase().equals("lab")) {
                    activities.remove("laboratory");
                } else if (activity_type.toLowerCase().equals("laboratory")) {
                    activities.remove("lab");
                }
                activities.remove(activity_type.toLowerCase());
            }
            answer = readString("Would you like to add another activity type? [Yes / No] ");
        } while (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y"));
        return new Room(number, availableSeats, building, possible_activities);
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printAddRoom() {
        System.out.println("Room added successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printRemoveRoom() {
        System.out.println("Room removed successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printUpdateRoom() {
        System.out.println("Room updated successfully.");
    }

    /*
    METHOD THAT PRINTS THE MENU FOR TEACHERS
     */

    private void printMenuTeachers() {
        System.out.println(
                "\tTeachers:\n" +
                        "\t\t0. Back;\n" +
                        "\t\t1. Get all teachers;\n" +
                        "\t\t2. Add a teacher to the repo;\n" +
                        "\t\t3. Remove a teacher from the repo (by index);\n" +
                        "\t\t4. Remove a teacher from the repo (by ID);\n" +
                        "\t\t5. Update a teacher from the repo (by index);\n" +
                        "\t\t6. Update a teacher from the repo (by ID);\n" +
                        "\t\t7. Serialize teachers;\n" +
                        "\t\t8. Deserialize teachers;" +
                        "\t\t9. Read teachers from database;"
        );
    }

    /*
    METHOD THAT PRINTS ALL TEACHERS FROM THE DISCIPLINES REPO
     */

    private void printAllTeachers() {
        for (int i = 0; i < this.ctrl.getAllTeachers().getSize(); ++i) {
            System.out.println("[" + i + "] " + this.ctrl.getAllTeachers().getAll().get(i));
        }
    }

    private Teacher readTeacher() throws TeacherException {
        int id = readInt("Please enter the teacher's ID: ");
        String firstName = readString("Please enter the teacher's first name: ");
        String lastName = readString("Please enter the teacher's last name: ");
        String title = readString("Please enter the teacher's title: ");
        String email = readString("Please enter the teacher's email: ");
        return new Teacher(id, firstName, lastName, title, email);
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printAddTeacher() {
        System.out.println("Teacher added successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printRemoveTeacher() {
        System.out.println("Teacher removed successfully.");
    }

    /*
    METHOD THAT PRINTS A CONFIRMATION MESSAGE TO THE SCREEN
     */

    private void printUpdateTeacher() {
        System.out.println("Teacher updated successfully.");
    }

    /*
    START METHOD
     */

    public void start() throws ActivityException, RoomException, TeacherException, DisciplineException, ActivityRepoException, DisciplineRepoException, FormationException, FormationRepoException, RoomRepoException, TeacherRepoException, IOException {
        printMenu();
        //variables used for reading some values
        int id;
        String fn;
        String ln;
        String title;
        String email;
        int seats;
        String building;
        String answer;
        String name;
        String field;
        int index;
        Formation subgroup = null;
        ArrayList<Integer> addActivities = new ArrayList<>();
        ArrayList<Integer> removeActivities = new ArrayList<>();
        ArrayList<String> activities = new ArrayList<>();
        String activity_type;
        ArrayList<String> possible_activities = new ArrayList<>();

        int command = readInt("Please enter a command: ");
        do {
            activities.add("lab");
            activities.add("laboratory");
            activities.add("seminar");
            activities.add("lecture");
            switch(command) {
                case 0:
                    System.out.println("Bye!!");
                    break;

                case 1:         //ACTIVITIES MENU
                    do {
                        printMenuActivities();
                        command = readInt("Please enter a command: ");
                        switch(command) {
                            case 0:
                                break;
                            case 1:
                                printAllActivities();
                                break;
                            case 2:
                                if (this.ctrl.addActivity(readActivity())) {
                                    printAddActivity();
                                }
                                break;
                            case 3:
                                index = readInt("Please enter the index at which you want to insert the activity: ");
                                this.ctrl.insertActivity(index, readActivity());
                                printInsertActivity();
                                break;
                            case 4:
                                index = readInt("Please enter the index at which you want to remove the activity: ");
                                if (this.ctrl.removeActivity(this.ctrl.getAllActivities().getAll().get(index))) {
                                    printRemoveActivity();
                                }
                                break;
                            case 5:
                                readActivitiesDB();
                                break;
                            default:
                                System.out.println("Please enter a valid command...");
                                break;
                        }
                    } while (command != 0);
                    printMenu();
                    command = readInt("Please enter a command: ");
                    break;

                case 2:         //DISCIPLINES MENU
                    do {
                        printMenuDisciplines();
                        command = readInt("Please enter a command: ");
                        switch(command) {
                            case 0:
                                break;
                            case 1:
                                printAllDisciplines();
                                break;
                            case 2:
                                if (this.ctrl.addDiscipline(readDiscipline())) {
                                    printAddDiscipline();
                                }
                                break;
                            case 3:
                                index = readInt("Please enter the index at which you want to remove the discipline: ");
                                if (this.ctrl.removeDiscipline(index)) {
                                    printRemoveDiscipline();
                                }
                                break;
                            case 4:
                                id = readInt("Please enter the ID of the discipline you wish to delete: ");
                                if (this.ctrl.removeDisciplineById(id)) {
                                    printRemoveDiscipline();
                                }
                                break;
                            case 5:
                                index = readInt("Please enter the index at which you want to update the discipline: ");
                                id = readInt("Please enter the discipline's new ID: ");
                                name = readString("Please enter the discipline's new name: ");
                                field = readString("Please enter the discipline's new filed: ");
                                this.ctrl.updateDiscipline(index, id, name, field);
                                printUpdateDiscipline();
                                break;
                            case 6:
                                id = readInt("Please enter the ID of the discipline which you want to update: ");
                                name = readString("Please enter the name of the discipline you wish to update: ");
                                field = readString("Please enter the discipline's new filed: ");
                                if (this.ctrl.updateDisciplineById(id, name, field)) {
                                    printUpdateDiscipline();
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid command...");
                                break;
                        }
                    } while (command != 0);
                    printMenu();
                    command = readInt("Please enter a command: ");
                    break;

                case 3:         //FORMATIONS MENU
                    do {
                        printMenuFormations();
                        command = readInt("Please enter a command: ");
                        switch(command) {
                            case 0:
                                break;
                            case 1:
                                printAllFormations();
                                break;
                            case 2:
                                if (this.ctrl.addFormation(readFormation())) {
                                    printAddFormation();
                                }
                                break;
                            case 3:
                                index = readInt("Please enter the index of the formation: ");
                                id = readInt("Please enter the formation's new ID: ");
                                name = readString("Please enter the formation's new name: ");
                                answer = readString("Do you wish to add activities to the formation?\n" +
                                        "(The activity will be added to the activities repo as well) [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    while (!answer.toLowerCase().equals("no") && !answer.toLowerCase().equals("n")) {
                                        answer = readString("Add activity? [Yes / No] ");
                                        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                            addActivities.add(readActivity().getId());
                                        }
                                    }
                                }
                                answer = readString("Do you wish to remove activities form the formation? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    while (!answer.toLowerCase().equals("no") && !answer.toLowerCase().equals("n")) {
                                        answer = readString("Remove activity? [Yes / No] ");
                                        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                            removeActivities.add(readActivity().getId());
                                        }
                                    }
                                }
                                answer = readString("Does this formation have a subgroup? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    answer = readString("Does the subgroup belong to the formations repository? [Yes / No] ");
                                    if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")) {
                                        subgroup = readFormation();
                                        this.ctrl.addFormation(subgroup);
                                    } else if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                        index = readInt("Please enter the subgroup's index from the repository:\n");
                                        printAllFormations();
                                        subgroup = this.ctrl.getAllFormations().getAll().get(index);
                                    }
                                }
                                this.ctrl.updateFormation(id, this.ctrl.getAllFormations().getAll().get(index), name, addActivities, removeActivities, subgroup);
                                printUpdateFormation();
                                addActivities.clear();
                                removeActivities.clear();
                                break;
                            case 4:
                                id = readInt("Please enter the ID of the formation which you want to update: ");
                                name = readString("Please enter the formation's name: ");
                                answer = readString("Do you wish to add activities to the formation?\n" +
                                        "(The activity will be added to the activities repo as well) [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    while (!answer.toLowerCase().equals("no") && !answer.toLowerCase().equals("n")) {
                                        answer = readString("Add activity? [Yes / No] ");
                                        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                            addActivities.add(readActivity().getId());
                                        }
                                    }
                                }
                                answer = readString("Do you wish to remove activities form the formation? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    while (!answer.toLowerCase().equals("no") && !answer.toLowerCase().equals("n")) {
                                        answer = readString("Remove activity? [Yes / No] ");
                                        if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                            removeActivities.add(readActivity().getId());
                                        }
                                    }
                                }
                                answer = readString("Does this formation have a subgroup? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    subgroup = readFormation();
                                }
                                this.ctrl.updateFormationByName(id, name, addActivities, removeActivities, subgroup);
                                printUpdateFormation();
                                addActivities.clear();
                                removeActivities.clear();
                                break;
                            case 5:
                                index = readInt("Please enter the index of the formation: ");
                                if (this.ctrl.removeFormation(this.ctrl.getAllFormations().getAll().get(index))) {
                                    printRemoveFormation();
                                }
                                break;
                            case 6:
                                name = readString("Please enter the formation's name: ");
                                if (this.ctrl.removeFormationByName(name)) {
                                    printRemoveFormation();
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid command...");
                                break;
                        }
                    } while (command != 0);
                    printMenu();
                    command = readInt("Please enter a command: ");
                    break;

                case 4:         //ROOMS MENU
                    do {
                        printMenuRooms();
                        command = readInt("Please enter a command: ");
                        switch(command) {
                            case 0:
                                break;
                            case 1:
                                printAllRooms();
                                break;
                            case 2:
                                if (this.ctrl.addRoom(readRoom())) {
                                    printAddRoom();
                                }
                                break;
                            case 3:
                                index = readInt("Please enter the room's index: ");
                                if (this.ctrl.removeRoom(this.ctrl.getAllRooms().getAll().get(index))) {
                                    printRemoveRoom();
                                }
                                break;
                            case 4:
                                name = readString("Please enter the room's number: ");
                                if (this.ctrl.removeRoomByNum(name)) {
                                    printRemoveRoom();
                                }
                                break;
                            case 5:
                                index = readInt("Please enter the room's index: ");
                                name = this.ctrl.getAllRooms().getAll().get(index).getRoomNumber();
                                answer = readString("Do you wish to change the room's number? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    name = readString("Please enter the room's new number: ");
                                }
                                seats = this.ctrl.getAllRooms().getAll().get(index).getAvailableSeats();
                                answer = readString("Do you wish to change the room's number of available seats? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    seats = readInt("Please enter the room's new number of available seats: ");
                                }
                                building = this.ctrl.getAllRooms().getAll().get(index).getBuilding();
                                answer = readString("Do you wish to change the room's building name? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    building = readString("Please enter the room's new building name: ");
                                }
                                System.out.println("You will need to choose all possible activity types for this room again.");
                                do {
                                    for (String activity : activities) {
                                        System.out.println(activity + ";");
                                    }
                                    activity_type = readString("Please choose an activity type to add to the room: ");
                                    if (activities.contains(activity_type.toLowerCase())) {
                                        possible_activities.add(activity_type);
                                        if (activity_type.toLowerCase().equals("lab")) {
                                            activities.remove("laboratory");
                                        } else if (activity_type.toLowerCase().equals("laboratory")) {
                                            activities.remove("lab");
                                        }
                                        activities.remove(activity_type.toLowerCase());
                                    }
                                    answer = readString("Would you like to add another activity type? [Yes / No] ");
                                } while (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y"));
                                this.ctrl.updateRoom(index, name, seats, building, possible_activities);
                                printUpdateRoom();
                                activities.clear();
                                break;
                            case 6:
                                name = readString("Please enter the room's number: ");
                                seats = this.ctrl.getAllRooms().getAll().get(this.ctrl.getAllRooms().getIndexByNum(name)).getAvailableSeats();
                                answer = readString("Do you wish to change the room's number of available seats? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    seats = readInt("Please enter the room's new number of available seats: ");
                                }
                                building = this.ctrl.getAllRooms().getAll().get(this.ctrl.getAllRooms().getIndexByNum(name)).getBuilding();
                                answer = readString("Do you wish to change the room's building name? [Yes / No] ");
                                if (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y")) {
                                    building = readString("Please enter the room's new building name: ");
                                }
                                System.out.println("You will need to choose all possible activity types for this room again.");
                                do {
                                    for (String activity : activities) {
                                        System.out.println(activity + ";");
                                    }
                                    activity_type = readString("Please choose an activity type to add to the room: ");
                                    if (activities.contains(activity_type.toLowerCase())) {
                                        possible_activities.add(activity_type);
                                        if (activity_type.toLowerCase().equals("lab")) {
                                            activities.remove("laboratory");
                                        } else if (activity_type.toLowerCase().equals("laboratory")) {
                                            activities.remove("lab");
                                        }
                                        activities.remove(activity_type.toLowerCase());
                                    }
                                    answer = readString("Would you like to add another activity type? [Yes / No] ");
                                } while (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y"));
                                if (this.ctrl.updateRoomByNum(name, seats, building, possible_activities)) {
                                    printUpdateRoom();
                                }
                                activities.clear();
                                break;
                            default:
                                System.out.println("Please enter a valid command...");
                                break;
                        }
                    } while (command != 0);
                    printMenu();
                    command = readInt("Please enter a command: ");
                    break;

                case 5:         //TEACHERS MENU
                    do {
                        printMenuTeachers();
                        command = readInt("Please enter a command: ");
                        switch(command) {
                            case 0:
                                break;
                            case 1:
                                printAllTeachers();
                                break;
                            case 2:
                                if (this.ctrl.addTeacher(readTeacher())) {
                                    printAddTeacher();
                                }
                                break;
                            case 3:
                                index = readInt("Please enter the teacher's index: ");
                                if (this.ctrl.removeTeacher(this.ctrl.getAllTeachers().getAll().get(index))) {
                                    printRemoveTeacher();
                                }
                                break;
                            case 4:
                                id = readInt("Please enter the teacher's ID: ");
                                if (this.ctrl.removeTeacherByID(id)) {
                                    printRemoveTeacher();
                                }
                                break;
                            case 5:
                                index = readInt("Please enter the teacher's index: ");
                                id = readInt("Please enter the teacher's new ID: ");
                                fn = readString("Please enter the teacher's new first name: ");
                                ln = readString("Please enter the teacher's new last name: ");
                                title = readString("Please enter the teacher's new title: ");
                                email = readString("Please enter the teacher's new email: ");
                                this.ctrl.updateTeacher(index, id, fn, ln, title, email);
                                printUpdateTeacher();
                                break;
                            case 6:
                                id = readInt("Please enter the teacher's ID: ");
                                fn = readString("Please enter the teacher's new first name: ");
                                ln = readString("Please enter the teacher's new last name: ");
                                title = readString("Please enter the teacher's new title: ");
                                email = readString("Please enter the teacher's new email: ");
                                if (this.ctrl.updateTeacherByID(id, fn, ln, title, email)) {
                                    printUpdateTeacher();
                                }
                                break;
                            case 7:
                                serializeTeachers();
                                break;
                            case 8:
                                deserializeTeachers();
                                break;
                            case 9:
                                readTeachersDB();
                                break;
                            default:
                                System.out.println("Please enter a valid command...");
                                break;
                        }
                    } while (command != 0);
                    printMenu();
                    command = readInt("Please enter a command: ");
                    break;
            }
        } while (command != 0);
    }
}
