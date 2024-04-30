# online_medical_clinic

Hi, this is a demo project of an application simulating the operation of a medical clinic on the Internet.
By logging in as a patient, you can choose a doctor with a specific specialization, 
check available appointments and register at a time convenient for you.
You can then view your visit history and from this level you can also delete your booking if your plans have changed.
By logging in as a doctor, you can add appointments by selecting dates and times in the calendar window. 
If you are going to work for several consecutive days in the same hours, you can select a wider range of dates. 
Then add the hours you will work and how long one visit should last. 
Visits according to this scheme will be created automatically.
You can browse published appointments for those that the patient has signed up for.
You can add a comment to your booked appointments as a Doctor.
You can use this logins when you want to login as a doctor: stefan_wątroba, aleksander_udarowski, ambroży_soczewka, alojzy_prostata; password: test.
You can use this logins when you want to login as a patient: norbert_choroba, robert_złamany, alicja_osteoporoza; password: test.
In order to be able to use the ICD APIs you need to follow these steps: 
Register to the ICD API site (https://icd.who.int/icdapi).
Click on the [Register] link and follow the instructions
Once you register and login to the portal with your account, you may now click the [View API access key] to get your clientid and clientsecret. These are necessary for using the APIs.
Then create file .env in main directory of the project using your clientid and clientsecret like in file .env.example located there.
The project is under development and new functionalities will be added.
