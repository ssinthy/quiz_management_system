# Quiz Management System

## Project Summary:
This is a simple role-based quiz system that allows:
- Admin users to add multiple-choice questions (MCQs) to a question bank.
- Student users to take a quiz based on the question bank.

The system uses two files for persistence:
- users.json: Stores user credentials and roles.
- quiz.json: Stores the MCQ question bank.

## Technology:
- Language: Java
- Data Storage: JSON files

## Prerequisite:
- Install JDK
- Import JSON.simple 

## Admin Work Flow
- Admin login to the system.
- Admin add MCQ questions to the question bank.

## Student Quiz Flow
- Student login to the system.
- If student want to perform quiz the system randomly selects 10 questions from the question bank.
- The student earns 1 mark for each correct answer.
- At the end of the quiz, the system shows the score and a message based on score.

##  Demo Admin login and adding questions
https://drive.google.com/file/d/1T5lxCD7nJgV5gjeAWS7KTUptrQtY1c_l/view?usp=drive_link

## Demo Student login and completing a quiz
https://drive.google.com/file/d/1e9GykOQgHpJHvd37D9UrPH-AJufJVJcc/view?usp=drive_link

