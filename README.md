# OneIndia
# Online Voting Platform

## Overview
OneIndia - The Online Voting Platform is a simple Java-based GUI application that allows users to cast votes for candidates representing different parties. It ensures that each voter votes only once and counts the votes for each party, displaying the results in the GUI. It was developed as part of my data structures lab project.

## Features
1. Cast votes using a 6-digit voter ID.
2. Select candidates from a predefined list.
3. Ensure each voter votes only once.
4. Count and display votes for each party.

## Prerequisites
* Java Development Kit (JDK) 8 or higher
* An Integrated Development Environment (IDE) like VSCode, IntelliJ IDEA, Eclipse, or NetBeans.

## Installation and Setup
1. Clone the repository:
    ```
    git clone https://github.com/your-username/voting-system.git
    cd voting-system

   ```

2. Open the project in your preferred IDE.
3. Compile and run the VotingSystem class.
    ```
    javac VotingSystem.java
    java VotingSystem

   ```

## Usage
1. Start the application: Run the VotingSystem class. A GUI window will appear.

2. Enter Voter ID: Input a 6-digit voter ID in the "Enter Voter ID" text field.

3. Select Candidate: Choose a candidate from the "Select Candidate" dropdown list.

4. Cast Vote: Click the "Cast Vote" button to cast your vote. The application will ensure that the voter ID is valid and unique.

5. Process Votes: Click the "Process Votes" button to display the total votes each party received.

## Code Structure
* Voter.java: Represents a voter with a unique voter ID and name.
* Vote.java: Represents a vote with a voter ID, candidate ID, and party.
* VotingSystem.java: Contains the main logic for the voting system, including casting votes, processing votes, and displaying the candidates and results.
   


