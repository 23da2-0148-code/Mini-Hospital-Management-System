package Src;

public class VisitLinkedList {
    Visit head; // first visit in the list

    public VisitLinkedList() {
        this.head = null;
    }

    // Add a new visit to the end of the list
    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
            return;
        }
        Visit current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newVisit;
    }

    // Remove a visit by its visitId
    public void removeVisit(int visitId) {
        if (head == null) {
            System.out.println("No visit history to remove from.");
            return;
        }

        if (head.visitId == visitId) {
            head = head.next;
            System.out.println("Visit ID " + visitId + " removed.");
            return;
        }

        Visit current = head;
        while (current.next != null && current.next.visitId != visitId) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Visit ID " + visitId + " not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Visit ID " + visitId + " removed.");
        }
    }

    // Search for a visit by its visitId
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.visitId == visitId) {
                return current;
            }
            current = current.next;
        }
        return null; // not found
    }

    // Display all visits in this patient's history
    public void displayVisits() {
        if (head == null) {
            System.out.println("No visit history available.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
}