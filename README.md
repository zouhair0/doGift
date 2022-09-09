# doGift

Companies can use Wedoogift services to distribute:

Gift deposits and Meal deposits


Gift deposits has 365 days lifespan, beyond this period it will no longer be counted in the user's balance.
example:
John receives a Gift distribution with the amount of $100 euros from Tesla. he will therefore have $100 in gift cards in his account.
He received it on 06/15/2021. The gift distribution will expire on 06/14/2022.


Meal deposit works like the Gift deposit excepting for the end date. In fact meal deposits expires at the end of February of the year following the distribution date.
example:
Jessica receives a Meal distribution from Apple with the amount of $50 on 01/01/2020, the distribution ends on 02/28/2021.

This is a Spring Boot project implement functions allowing companies to distribute gift and meal deposits to a user if the company balance allows it. And calculate the user's balance.

## Run / Build (Spring Boot project)
- Import the project in Eclipse / Intellij as a maven project
- Run ChallengeApplication.java as a Java application 
- Launch 'mvn clean install' to build the project and generate a jar with embedded dependencies
- Then, you can use APIs to creat objects ans deposit gifts/meals (default port : 9090)
- Exemple : localhost:9090/gift/1/3/500

This API, send a deposit gift of 500 euros from the company with ID=3 to user with ID=1 
