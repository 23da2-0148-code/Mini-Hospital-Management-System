package Src;

public class EmergencyQueue {

    
    private class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private QueueNode front; // next patient to be treated
    private QueueNode rear;  // last patient who joined the queue

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
    }

    
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Patient " + patient.name + " added to the waiting queue.");
    }

    
    public Patient dequeue() {
        if (front == null) {
            System.out.println("No patients waiting in the queue.");
            return null;
        }
        Patient treatedPatient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        return treatedPatient;
    }

    
    public void displayQueue() {
        if (front == null) {
            System.out.println("No patients currently waiting.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    
    public boolean isEmpty() {
        return front == null;
    }
}