package Src;

public class TreatmentStack {

    // A simple record of one completed treatment
    public class TreatmentRecord {
        int patientId;
        String patientName;
        String medicalCondition;
        String treatmentDetails;

        TreatmentRecord(int patientId, String patientName, String medicalCondition, String treatmentDetails) {
            this.patientId = patientId;
            this.patientName = patientName;
            this.medicalCondition = medicalCondition;
            this.treatmentDetails = treatmentDetails;
        }

        @Override
        public String toString() {
            return "Patient ID: " + patientId + ", Name: " + patientName +
                   ", Condition: " + medicalCondition + ", Treatment: " + treatmentDetails;
        }
    }

    // Internal stack node
    private class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private StackNode top; // most recently completed treatment

    public TreatmentStack() {
        this.top = null;
    }

    // ---------- PUSH ----------
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        System.out.println("Treatment record added for Patient ID " + record.patientId + ".");
    }

    // ---------- POP ----------
    public TreatmentRecord pop() {
        if (top == null) {
            System.out.println("No treatment records available.");
            return null;
        }
        TreatmentRecord removed = top.record;
        top = top.next;
        return removed;
    }

    // ---------- DISPLAY ----------
    public void displayStack() {
        if (top == null) {
            System.out.println("No treatment records to display.");
            return;
        }
        StackNode current = top;
        System.out.println("Most recent treatments first:");
        while (current != null) {
            System.out.println("- " + current.record);
            current = current.next;
        }
    }

    // ---------- HELPER ----------
    public boolean isEmpty() {
        return top == null;
    }
}