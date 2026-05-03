# 🖨️ Print Spooling System (Java)

# Overview
The **Print Spooling System** is a Java-based desktop application that simulates how an Operating System manages multiple print jobs. It demonstrates core OS concepts like **FIFO scheduling, multithreading, and job queue management** using a user-friendly GUI.

This project is developed as part of the **Operating Systems Laboratory**.

---

##  Features
-  Add new print jobs  
-  Remove jobs using Job ID  
-  View job details and status  
-  FIFO (First-In-First-Out) queue implementation  
-  Multithreading for realistic print simulation  
-  Interactive GUI using Java Swing  

---

##  Technologies Used
- Java **
- **Java Swing **
- **Multithreading**
- **Queue (LinkedList)**

---

## Key Concepts Implemented
-  Process Scheduling  
-  Job Queue Management (FIFO)  
-  Multithreading  
-  GUI-based User Interaction  

---

##  Project Structure
📁 Print-Spooling-System
┣ 📄 Job.java
┣ 📄 JobQueue.java
┣ 📄 AddJob.java
┣ 📄 RemoveJob.java
┣ 📄 ViewJob.java
┣ 📄 printHome.java
┗ 📄 README.md

---

##  How It Works
1. User adds a print job (name, copies, file type)
2. Job is added to a FIFO queue
3. A separate thread simulates printing
4. Status updates from *Printing* → *Completed*
5. User can view or remove jobs anytime

---

##  How to Run
1. Install **Java JDK 17+**
2. Open project in any IDE (NetBeans / IntelliJ / Eclipse)
3. Run `printHome.java`
4. Use the GUI to interact with the system

---

## Sample Logic
```java
Thread.sleep(job.getNumberOfCopies() * 2000);
