package Src;

import java.util.Scanner;

public class Main {

    static PatientBST patientBST = new PatientBST();
    static EmergencyQueue emergencyQueue = new EmergencyQueue();
    static TreatmentStack treatmentStack = new TreatmentStack();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: registerPatient(); break;
                case 2: addToQueue(); break;
                case 3: treatNextPatient(); break;
                case 4: searchPatient(); break;
                case 5: deletePatient(); break;
                case 6: patientBST.inorderTraversal(); break;
                case 7: emergencyQueue.displayQueue(); break;
                case 8: treatmentStack.displayStack(); break;
                case 9: manageVisitHistory(); break;
                case 0: System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Mini Hospital Emergency Management System =====");
        System.out.println("1. Register new patient");
        System.out.println("2. Add patient to emergency queue");
        System.out.println("3. Treat next patient (dequeue + record treatment)");
        System.out.println("4. Search patient (BST)");
        System.out.println("5. Delete patient (BST)");
        System.out.println("6. View all patients (in-order)");
        System.out.println("7. View waiting queue");
        System.out.println("8. View treatment history (stack)");
        System.out.println("9. Manage a patient's visit history");
        System.out.println("0. Exit");
    }

    private static void registerPatient() {
        int id = readInt("Patient ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age = readInt("Age: ");
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Patient newPatient = new Patient(id, name, age, contact, condition);
        patientBST.insert(newPatient);
        System.out.println("Patient registered successfully.");
    }

    private static void addToQueue() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("No patient found with that ID. Please register first.");
            return;
        }
        emergencyQueue.enqueue(p);
    }

    private static void treatNextPatient() {
        Patient p = emergencyQueue.dequeue();
        if (p == null) return;

        System.out.println("Now treating: " + p);
        System.out.print("Enter treatment given: ");
        String treatment = scanner.nextLine();

        TreatmentStack.TreatmentRecord record = treatmentStack.new TreatmentRecord(
                p.patientId, p.name, p.medicalCondition, treatment
        );
        treatmentStack.push(record);

        int visitId = (int) (Math.random() * 9000) + 1000;
        Visit visit = new Visit(visitId, "Today", "Dr. Duty", p.medicalCondition, treatment);
        p.visitHistory.addVisit(visit);

        System.out.println("Treatment completed and recorded.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println("Found: " + p);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        patientBST.delete(id);
    }

    private static void manageVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("1. Add visit  2. Remove visit  3. Search visit  4. Display all visits");
        int subChoice = readInt("Choice: ");

        switch (subChoice) {
            case 1:
                int visitId = readInt("Visit ID: ");
                System.out.print("Visit Date: ");
                String date = scanner.nextLine();
                System.out.print("Doctor Name: ");
                String doctor = scanner.nextLine();
                System.out.print("Diagnosis: ");
                String diagnosis = scanner.nextLine();
                System.out.print("Treatment: ");
                String treatment = scanner.nextLine();
                p.visitHistory.addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
                break;
            case 2:
                int removeId = readInt("Enter Visit ID to remove: ");
                p.visitHistory.removeVisit(removeId);
                break;
            case 3:
                int searchId = readInt("Enter Visit ID to search: ");
                Visit found = p.visitHistory.searchVisit(searchId);
                System.out.println(found == null ? "Visit not found." : "Found: " + found);
                break;
            case 4:
                p.visitHistory.displayVisits();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}