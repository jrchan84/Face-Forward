# BizHacks-2020

BizHacks is an annual 24 hour hackathon hosted at UBC with a focus in creating solutions to real-life business challenges.
In 2020, the challenge was centered around a BestBuy case study to address their opportunities and problems.

My team chose to contribute to one of the opportunities that gave us lots of freedom: To improve their customer interactions with innovative technology.
Hence, FaceForward, an application that provides a personalized customer experience. Our team implemented a working UI with a a straightforward backend connected to the Microsoft Azure Facial Recognition API and the BestBuy Marketplace Product API to provide a personalized shopping interaction to consenting customers. The goal was to showcase the power and accessibility of modern cloud technologies while also impressing judges with our unique solution.

UI:

I played the primary role in constructing the UI with JavaFX to work with the APIs and backend of the program. The UI would respond to known customers and show a prompt regarding shopping choices and employee help, which a customer could respond to via buttons


Technologies:

Java, JavaFX, Microsoft Azure API, BestBuy Marketplace API, JSON, Miscellaneous command-line scripts.

Presentation:

To showcase our project and highlight the privacy concerns, we demoed the program with a trial with an "consenting" customer. ie, the program would only respond to users who have expressed their consent to using the service and not others who the program doesn't recognize. We achieved this by storing a local image of a user and using the API to create a FaceID. Then, during the demo, we would connect to the API with two different people, one with a matching FaceID and one without. Only the matching user would be able to interact with the programs prompts.

This project was an excellent experience in working with API's and provided rewarding exposure to cloud technologies.
