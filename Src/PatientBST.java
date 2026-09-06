package Src;

public class PatientBST {
    Patient root;

    public PatientBST() {
        this.root = null;
    }

    // ---------- INSERT ----------
    public void insert(Patient newPatient) {
        root = insertRec(root, newPatient);
    }

    private Patient insertRec(Patient current, Patient newPatient) {
        if (current == null) {
            return newPatient;
        }
        if (newPatient.patientId < current.patientId) {
            current.left = insertRec(current.left, newPatient);
        } else if (newPatient.patientId > current.patientId) {
            current.right = insertRec(current.right, newPatient);
        } else {
            System.out.println("Patient ID " + newPatient.patientId + " already exists.");
        }
        return current;
    }

    // ---------- SEARCH ----------
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(Patient current, int patientId) {
        if (current == null) {
            return null; // not found
        }
        if (patientId == current.patientId) {
            return current;
        } else if (patientId < current.patientId) {
            return searchRec(current.left, patientId);
        } else {
            return searchRec(current.right, patientId);
        }
    }

    // ---------- DELETE ----------
    public void delete(int patientId) {
        root = deleteRec(root, patientId);
    }

    private Patient deleteRec(Patient current, int patientId) {
        if (current == null) {
            System.out.println("Patient ID " + patientId + " not found.");
            return null;
        }

        if (patientId < current.patientId) {
            current.left = deleteRec(current.left, patientId);
        } else if (patientId > current.patientId) {
            current.right = deleteRec(current.right, patientId);
        } else {
            // Found the node to delete

            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }
            // Case 2: one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            // Case 3: two children — find the smallest value in the right subtree
            Patient successor = findMin(current.right);
            // Copy successor's data into current node
            current.patientId = successor.patientId;
            current.name = successor.name;
            current.age = successor.age;
            current.contactNumber = successor.contactNumber;
            current.medicalCondition = successor.medicalCondition;
            current.visitHistory = successor.visitHistory;
            // Delete the successor from the right subtree
            current.right = deleteRec(current.right, successor.patientId);
        }
        return current;
    }

    private Patient findMin(Patient node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records available.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Patient current) {
        if (current != null) {
            inorderRec(current.left);
            System.out.println(current);
            inorderRec(current.right);
        }
    }
}