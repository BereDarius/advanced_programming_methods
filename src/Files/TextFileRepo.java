package Files;

import Domain.*;
import Exceptions.*;
import Repos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFileRepo {
    private File file_name;

    public TextFileRepo(String file) {
        this.file_name = new File(file);
    }

    public void populateFile(ActivityRepo ar, DisciplineRepo dr, FormationRepo fr, RoomRepo rr, TeacherRepo tr) throws IOException {
        FileWriter fileWriter = new FileWriter("Repo.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Activity activity : ar.getAll()) {
            bufferedWriter.write("activity " + activity.getId() + " "
                    + activity.getDiscipline() + " "
                    + activity.getActivityType() + " "
                    + activity.getTeacher() + " "
                    + activity.getRoom());
            bufferedWriter.newLine();
        }
        for (Discipline discipline : dr.getAll()) {
            bufferedWriter.write("discipline " + discipline.getId() + " "
                    + discipline.getName().replaceAll(" ", "_") + " "
                    + discipline.getField().replaceAll(" ", "_"));
            bufferedWriter.newLine();
        }
        for (Formation formation : fr.getAll()) {
            bufferedWriter.write("formation " + formation.getId() + " "
                    + formation.getName() + " "
                    + formation.getActivities().toString().replaceAll(" ", ""));
            bufferedWriter.newLine();
        }
        for (Room room : rr.getAll()) {
            bufferedWriter.write("room " + room.getRoomNumber() + " "
                    + room.getAvailableSeats() + " "
                    + room.getBuilding().replaceAll(" ", "_") + " "
                    + room.getActivityTypes().toString().replaceAll(" ", ""));
            bufferedWriter.newLine();
        }
        for (Teacher teacher : tr.getAll()) {
            bufferedWriter.write("teacher " + teacher.getId() + " "
                    + teacher.getFirstName() + " "
                    + teacher.getLastName() + " "
                    + teacher.getTitle() + " "
                    + teacher.getEmail());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public void populateRepos(ActivityRepo ar, DisciplineRepo dr, FormationRepo fr, RoomRepo rr, TeacherRepo tr) throws IOException, ActivityException, ActivityRepoException, DisciplineException, FormationException, FormationRepoException, DisciplineRepoException, RoomException, RoomRepoException, TeacherException, TeacherRepoException {
        String line;
        FileReader fileReader = new FileReader("Repo.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] result;
        String[] auxiliary;
        Integer[] auxiliary2;
        while ((line = bufferedReader.readLine()) != null) {
            result = line.split("\\s");
            if (result[0].equals("activity")) {
                ar.add(new Activity(Integer.parseInt(result[1]), Integer.parseInt(result[2]), result[3], Integer.parseInt(result[4]), result[5]));
            }
            if (result[0].equals("discipline")) {
                dr.add(new Discipline(Integer.parseInt(result[1]), result[2].replaceAll("_", " "), result[3].replaceAll("_", " ")));
            }
            if (result[0].equals("formation")) {
                auxiliary = result[3].substring(1, result[3].length()-1).split(",");
                auxiliary2 = new Integer[auxiliary.length];
                for (int i = 0; i<auxiliary.length; ++i) {
                    auxiliary2[i] = Integer.parseInt(auxiliary[i]);
                }
                Formation formation = new Formation(Integer.parseInt(result[1]), result[2], new ArrayList<>(Arrays.asList(auxiliary2)));
                fr.add(formation);
            }
            if (result[0].equals("room")) {
                auxiliary = result[4].substring(1, result[4].length()-1).split(",");
                Room room = new Room(result[1], Integer.parseInt(result[2]), result[3].replaceAll("_", " "), new ArrayList<>(Arrays.asList(auxiliary)));
                rr.add(room);
            }
            if (result[0].equals("teacher")) {
                tr.add(new Teacher(Integer.parseInt(result[1]), result[2], result[3], result[4], result[5]));
            }
        }
    }

    public void clearFile() {
        if (this.file_name.delete()) {
            System.out.println("File cleared");
        }
    }
}
