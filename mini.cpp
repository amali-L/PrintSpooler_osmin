#include <iostream>
#include <string>
#include <fstream>
using namespace std;

class Complaint {
public:
    int complaintID;
    int citizenid;
    string category;
    string details;
    string status;

    Complaint() {
        complaintID = 0;
        citizenid = 0;
        category = "";
        details = "";
        status = "Pending";
    }

    Complaint(int id, int cID, string cat, string det) {
        complaintID = id;
        citizenid = cID;
        category = cat;
        details = det;
        status = "Pending";
    }

    friend ostream& operator<<(ostream &tout,  Complaint &c);
    friend istream& operator>>(istream &tin, Complaint &c);
};

istream& operator>>(istream &tin, Complaint &c) {
    cout << "Enter Complaint ID: ";
    tin >> c.complaintID;
    cout << "Enter Citizen ID: ";
    tin >> c.citizenid;
    tin.ignore();
    cout << "Enter Complaint Details: ";
    getline(tin, c.details);
    c.status = "Pending";
    return tin;
}

ostream& operator<<(ostream &tout, Complaint &c) {
    tout << "Complaint ID: " << c.complaintID
         << "\nCitizen ID: " << c.citizenid
         << "\nCategory: " << c.category
         << "\nDetails: " << c.details
         << "\nStatus: " << c.status << endl;
    return tout;
}

class Queue {
private:
    int rear, front;
    Complaint arr[100];
    int size;
    string category;

public:
    Queue(string cat = "") {
        size = 100;
        front = 0;
        rear = -1;
        category = cat;
    }

    bool isEmpty() {
        return (front > rear);
    }

    string getFilePrefix() {
        if (category == "Cleaning") 
            return "Cleaning";
        else if (category == "Repair Work") 
            return "Repair";
        else if (category == "Society Welfare") 
            return "Society";
        else if (category == "Water Supply") 
            return "Water";
        else if (category == "Electricity") 
            return "Electricity";
        else 
           return "General";
    }

    void enqueue( Complaint &c) {
        if (rear == size - 1) {
            cout << "Queue is Full!" << endl;
            return;
        }
        arr[++rear] = c;

        string prefix = getFilePrefix();
        ofstream reg(prefix + "_Registered.txt", ios::app);
        ofstream pend(prefix + "_Pending.txt", ios::app);

        reg << "Complaint ID: " << c.complaintID
            << ", Citizen ID: " << c.citizenid
            << ", Category: " << c.category
            << ", Details: " << c.details
            << ", Status: Pending\n";

        pend << "Complaint ID: " << c.complaintID
             << ", Citizen ID: " << c.citizenid
             << ", Category: " << c.category
             << ", Details: " << c.details
             << ", Status: Pending\n";

        reg.close();
        pend.close();

        cout << "Complaint " << c.complaintID << " added successfully to " 
             << c.category << " department!" << endl;
    }

    Complaint dequeue() {
        if (isEmpty()) {
            cout << "Queue is Empty!" << endl;
            return Complaint();
        }
        return arr[front++];
    }

    void displayAll() {
        if (isEmpty()) {
            cout << "No complaints in queue!" << endl;
            return;
        }
        cout << "\n========== COMPLAINTS (" << category << ") ==========\n";
        for (int i = front; i <= rear; i++) {
            cout << arr[i];
            cout << "-----------------------------------\n";
        }
    }
};

class Officer {
public:
    int officerID;
    string name;
    string department;
    string designation;

    Officer() {
        officerID = 0;
        name = "";
        department = "";
        designation = "";
    }

    Officer(int id, string n, string dept, string desig) {
        officerID = id;
        name = n;
        department = dept;
        designation = desig;
    }

    void resolveComplaint(Complaint &c) {
        cout << "\nOfficer " << name << " (" << designation 
             << ") is resolving Complaint " << c.complaintID 
             << " - " << c.details << endl;
        c.status = "Resolved";

        string prefix;
        if (c.category == "Cleaning") 
            prefix = "Cleaning";
        else if (c.category == "Repair Work") 
            prefix = "Repair";
        else if (c.category == "Society Welfare") 
           prefix = "Society";
        else if (c.category == "Water Supply") 
           prefix = "Water";
        else if (c.category == "Electricity") 
           prefix = "Electricity";
        else 
           prefix = "General";

        ofstream res(prefix + "_Resolved.txt", ios::app);
        res << "Complaint ID: " << c.complaintID
            << ", Citizen ID: " << c.citizenid
            << ", Category: " << c.category
            << ", Details: " << c.details
            << ", Resolved by: " << name << " (" << designation << ", " << department << ")"
            << ", Status: Resolved\n";
        res.close();

    
        ifstream fin(prefix + "_Pending.txt");
        ofstream temp("Temp.txt");
        string line;
        string search = "Complaint ID: " + to_string(c.complaintID);
        while (getline(fin, line)) {
            if (line.find(search) == string::npos)
                temp << line << "\n";
        }
        fin.close();
        temp.close();
        remove((prefix + "_Pending.txt").c_str());
        rename("Temp.txt", (prefix + "_Pending.txt").c_str());

        cout << "Status updated to: Resolved\n";
    }

    friend ostream& operator<<(ostream &tout,  Officer &o);
    friend istream& operator>>(istream &tin, Officer &o);
};

istream& operator>>(istream &tin, Officer &o) {
    cout << "Enter Officer ID: ";
    tin >> o.officerID;
    tin.ignore();
    cout << "Enter Officer Name: ";
    getline(tin, o.name);
    cout << "Enter Department: ";
    getline(tin, o.department);
    cout << "Enter Designation: ";
    getline(tin, o.designation);
    return tin;
}

ostream& operator<<(ostream &tout,  Officer &o) {
    tout << "Officer ID: " << o.officerID
         << ", Name: " << o.name
         << ", Department: " << o.department
         << ", Designation: " << o.designation;
    return tout;
}

void displayMenuC() {
    cout << "\n========= CITIZEN MENU =========\n";
    cout << "1. Add New Complaint\n";
    cout << "2. View Complaints Status\n";
    cout << "3. Exit\n";
    cout << "Enter your choice: ";
}

void displayMenuO() {
    cout << "\n========= OFFICER MENU =========\n";
    cout << "1. Register Officer\n";
    cout << "2. Resolve Complaint\n";
    cout << "3. View Complaints (Files)\n";
    cout << "4. Exit\n";
    cout << "Enter your choice: ";
}

void displayCategory() {
    cout << "\n======== CATEGORY ==========\n";
    cout << "1. Cleaning\n";
    cout << "2. Repair Work\n";
    cout << "3. Society Welfare\n";
    cout << "4. Water Supply\n";
    cout << "5. Electricity\n";
    cout << "Enter your choice: ";
}

int main() {
    Queue qc("Cleaning"), qr("Repair Work"), qs("Society Welfare"), qw("Water Supply"), qe("Electricity");
    Officer oc, orr, os, ow, oe;
    int mainChoice;

    cout << "Welcome to Complaint Management System\n";

    while (true) {
        cout << "\nSelect Role:\n1. Citizen\n2. Officer\n3. Exit\nChoice: ";
        cin >> mainChoice;

        if (mainChoice == 1) {
            int choice;
            displayMenuC();
            cin >> choice;

            if (choice == 1) {
                int catChoice;
                displayCategory();
                cin >> catChoice;
                cin.ignore();

                string category;
                switch (catChoice) {
                    case 1: category = "Cleaning"; 
                            break;
                    case 2: category = "Repair Work"; 
                            break;
                    case 3: category = "Society Welfare"; 
                            break;
                    case 4: category = "Water Supply"; 
                            break;
                    case 5: category = "Electricity"; 
                            break;
                    default: category = "General"; 
                             break;
                }

                Complaint c;
                cout << "\n--- Enter Complaint Details ---\n";
                cin >> c;
                c.category = category;

                if (category == "Cleaning") 
                    qc.enqueue(c);
                else if (category == "Repair Work") 
                    qr.enqueue(c);
                else if (category == "Society Welfare") 
                    qs.enqueue(c);
                else if (category == "Water Supply") 
                    qw.enqueue(c);
                else if (category == "Electricity") 
                    qe.enqueue(c);

            } else if (choice == 2) {
                displayCategory();
                int catChoice;
                cin >> catChoice;
                string prefix;

                switch (catChoice) {
                    case 1: prefix = "Cleaning"; 
                           break;
                    case 2: prefix = "Repair"; 
                           break;
                    case 3: prefix = "Society"; 
                           break;
                    case 4: prefix = "Water"; 
                           break;
                    case 5: prefix = "Electricity"; 
                           break;
                    default: prefix = "General"; 
                           break;
                }

                cout << "\n--- Viewing Pending Complaints ---\n";
                ifstream pend(prefix + "_Pending.txt");
                string line;
                while (getline(pend, line)) cout << line << endl;
                pend.close();
            } else if (choice == 3) {
                cout << "Returning to main menu...\n";
            } else {
                cout << "Invalid option.\n";
            }
        }

        else if (mainChoice == 2) {
            int choice;
            displayMenuO();
            cin >> choice;

            if (choice == 1) {
                displayCategory();
                int catChoice;
                cin >> catChoice;
                cin.ignore();

                switch (catChoice) {
                    case 1: cin >> oc; 
                            break;
                    case 2: cin >> orr; 
                            break;
                    case 3: cin >> os; 
                            break;
                    case 4: cin >> ow; 
                            break;
                    case 5: cin >> oe; 
                            break;
                    default: cout << "Invalid category.\n"; 
                            break;
                }

            } else if (choice == 2) {
                displayCategory();
                int catChoice;
                cin >> catChoice;

                Complaint next;
                switch (catChoice) {
                    case 1: if (!qc.isEmpty()) { 
                        next = qc.dequeue(); 
                        oc.resolveComplaint(next); 
                    } else 
                    cout << "No complaints!\n"; 
                    break;
                    case 2: if (!qr.isEmpty()) {
                         next = qr.dequeue(); 
                         orr.resolveComplaint(next); 
                        } else cout << "No complaints!\n"; 
                        break;
                    case 3: if (!qs.isEmpty()) {
                         next = qs.dequeue(); 
                         os.resolveComplaint(next); 
                        } else cout << "No complaints!\n"; 
                        break;
                    case 4: if (!qw.isEmpty()) { 
                        next = qw.dequeue(); 
                        ow.resolveComplaint(next); 
                    } else cout << "No complaints!\n"; 
                          break;
                    case 5: if (!qe.isEmpty()) { 
                        next = qe.dequeue(); 
                        oe.resolveComplaint(next); 
                    } else cout << "No complaints!\n"; 
                          break;
                    default: cout << "Invalid category.\n"; 
                             break;
                }

            } else if (choice == 3) {
                displayCategory();
                int catChoice;
                cin >> catChoice;
                string prefix;

                switch (catChoice) {
                    case 1: prefix = "Cleaning";
                            break;
                    case 2: prefix = "Repair"; 
                            break;
                    case 3: prefix = "Society";
                            break;
                    case 4: prefix = "Water"; 
                            break;
                    case 5: prefix = "Electricity"; 
                            break;
                    default: prefix = "General"; 
                            break;
                }

                cout << "\n--- Viewing All Files for " << prefix << " ---\n";
                cout << "\nRegistered:\n";
                {
                    ifstream reg(prefix + "_Registered.txt");
                    string line;
                    while (getline(reg, line)) 
                      cout << line << endl;
                }
                cout << "\nPending:\n";
                {
                    ifstream pend(prefix + "_Pending.txt");
                    string line;
                    while (getline(pend, line)) 
                       cout << line << endl;
                }
                cout << "\nResolved:\n";
                {
                    ifstream res(prefix + "_Resolved.txt");
                    string line;
                    while (getline(res, line)) 
                       cout << line << endl;
                }
            } else if (choice == 4) {
                cout << "Returning to main menu...\n";
            } else {
                cout << "Invalid input.\n";
            }
        }

        else if (mainChoice == 3) {
            cout << "Exiting Complaint Management System.\n";
            break;
        }

        else {
            cout << "Invalid role selected.\n";
        }
    }
    return 0;
}
