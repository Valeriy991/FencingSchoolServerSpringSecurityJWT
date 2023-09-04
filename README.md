# FencingSchoolServer
Project on the topic "Development of a client-server application
Java Enterprise Edition"
Used tools and technologies:
• Java EE
• Apache Tomcat
• SpringBoot, SpringData
• MySQL DB
• Java FX
•Jackson

Technical task:
Develop a client-server application using the above technologies to create, modify, delete fencing school administrator records. To do this, implement the following set of Java classes and database tables corresponding to them.
1. User - system user, has the following fields
• id
• login
• password
• name
• regDate
2. Apprentice is a fencing school student who attends training sessions with various coaches. Has the following fields
• id
• surname
• name
• patronymic
• phoneNumber
3. Trainer - a fencing school coach who conducts training for students. Each coach has an experience measured in the number of years worked in this field. Has the following fields
• id
• surname
• name
• patronymic
• experience
4. TrainerSchedule - the schedule of the trainer, shows what days of the week at what time the trainer conducts classes, fields:
• idTrainer
• MondayStart
• MondayEnd
• tuesdayStart
• tuesdayEnd
• weekdayStart
• wednesdayEnd
• ThursdayStart
• thursdayEnd
• fridayStart
• fridayStart
• SaturdayStart
• saturdayEnd
• SundayStart
• sundayEnd
5. Training - training conducted at a fencing school. It has the number of the hall, the date and time of the lesson, as well as the coach and the student. When adding a new training session for a trainer, it must be taken into account that the trainer cannot receive more than 3 students at the same time, and also does not receive appointments after working hours. Only 10 students can work in the hall at the same time. A student cannot attend several training sessions at once on the same day.
The workout lasts 90 minutes.
• id
• numberGym
• trainer
• Apprentice
• date
• timeStart
Relationships between database tables should be implemented in the form:
Trainer - TrainerSchedule: one to one, Apprentice - Training: 1 to many, Trainer - Training: 1 to many

Application server side:
A REST API is being developed that generates responses in JSON format. For each entity of the data model, develop a repository for accessing the database in accordance with the API of the server application. Server application classes:
1.UserController
Methods:
• post – receives data and registers a new school administrator user in the system. Correctly handles the existence of a user in the database
• get – checks if the username and password match in the database
• get – displays the user with the given id
• delete – removes the user with the given id from the database
2.ApprenticeController
Methods:
• post – adds a new student to the database
• get – receives all students, as well as a student by his id
• put - updates the student by his id
• delete - removes the student and all records associated with him
3. TrainerController
Methods:
• post – adds a new coach to the database
• get – receives all trainers, as well as a trainer by his id
• put – updates the coach by his id
• delete – removes a coach from the database by his id
4. TrainerScheduleController
Methods:
• post – adds a schedule for a specific coach
• get – retrieves the schedule for the coach with the given id
• put – updates the schedule of the coach with the given id
• delete – deletes the coach's schedule with the given id
5.TrainingController
Methods:
• post – adds a new workout for the given user id and for the given coach id
• get – retrieves a workout by its id, workouts by user id or trainer id
• delete – deleting a workout by its id

Client side of the application
Represents a set of GUI forms that interact with each other. The forms access the API of the server application via an http connection, send a request to the server and receive a response from it in JSON format, then parse it and display the results as user interface elements. A set of forms and client-side classes:
1. Program - the main class of the application. Checks for a previously authorized user in the system, if the user is authorized, then there is a transition to the main form of the MainController application, if not, then a transition is made to the AuthorizationController to carry out the authorization procedure
2. AuthorizationController - a form that performs the authorization procedure in the system. Allows you to enter a username and password and log in. It is also possible from this form to go to the registration form for a new user RegistrationController or, if successful, to MainController
3. RegistrationController - a form that allows you to register a new user in the system, after successful registration, the transition is made to the AuthorizationController form
4.MainController
