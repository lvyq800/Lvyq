The test is designed based on springframework for backend and angularjs for frontend.
springframework is used due to that it provides excellent support for both restful service and architecture patterns such as MVC and micro service.
using springframework, the developer can be less worry about many detailed technologies such as json data transfering, data encription, etc.
Another reason to choose springframwork is that it client can be used as a light-weight web server to support small project development.

Angularjs is used to develop frontend side because it is great in implementing page UI interactivity, and it is brilliant in bridging frontend and backend with the fewest code and great performance.

An executable program is provided for the backend - by running it, the backend will be running on port 8080.
To start the frontend side, please run it based on 4200 port, for example, by running "spring run app.groovy -- --server.port=4200" based on the root folder of the project.

The persistent layer could be based on relational data base such as mysql and sqlserver, depending on the scope of the project. Simultaneously, hibernate, as the most efficient entity layer implementation, can be used in the project.
Alternatively, non-sql database such as mongoDB can be used if relational query is less required.

I spent sometime to design and invest, totally around 2 hours used to code, test, and adjust functions. 

Forgot to mention the usage of the system.
1. open http://localhost:4200 (or real IP if you run it remotely) => the page opens and available goods/count is listed;
2. by choosing a user under the goods list => the users purchasing interface and a personal goods list is given;
3. fill in count numbers for goods and click on "buy" button => the buying activity will be submited and these two lists will be refreshed afterwards.
4. try to choose different users to see the result.
5. the user selection list provides only two users, however, the backend program can support unlimited users.

Weaknesses of the system due to lack of data verifications:
1. you can buy negative number of goods and the available goods number can be more than the original number;
2. the available goods number can be negative since users can buy more goods than the original number.
