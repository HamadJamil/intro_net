IntroNet - Social Network Friend Recommendation System
Project Overview
IntroNet is a social network application that utilizes graph theory concepts to model user relationships and provide insightful social recommendations. In this system, each user is represented as a node in a graph, and friendships are represented by edges connecting these nodes. The system offers key functionalities like mutual friends, friend recommendations, and a real-time user feed, mirroring features commonly found on social media platforms.

Key Features
Add Friends: Allows users to create new friendships within the network.
Delete Friends: Allows users to break existing friendships.
Display Mutual Friends: Helps users find mutual friends they share with another user.
Suggest Potential Friends: Recommends friends based on common friends-of-friends.
User Posting System: Lets users create posts that are stored and displayed in a First-Come, First-Served (FCFS) manner, simulating a real-time activity feed.
Technologies Used
JavaFX: Used for creating the graphical user interface (GUI).
IntelliJ IDEA: The IDE used for development.
Java: The primary programming language for the application logic.
MySQL (Optional): For optional persistent data storage (e.g., user details, friendships, and posts).
GitHub: Used for version control and project management.
Pages & Features
1. Login Page
Fields:
Email
Password
Buttons:
Sign In
Forgot Password?
Transitions:
Transition to the Sign Up page.
2. Sign Up Page
Fields:
Username
Email
Password
Buttons:
Sign Up
Transition to the Sign In page.
3. User Profile Page
Components:
Profile Picture
Username
Friend Request Button: To add a friend.
Picture Feed: Displays a user's posts in chronological order.
4. User Class
Attributes:

Picture: User's profile picture.
Name: User's name.
Email: User's email.
Password: User's password.
Friend List: List of friends (edges in the graph).
Picture Queue: A queue to store user posts in FCFS order.
User Actions:

Add Friends: Send friend requests and establish friendships.
Remove Friends: Unfriend someone.
Make Posts: Create and optionally edit posts. Posts are displayed in FCFS order.
Update Personal Information: Users can update their profile details like name, email, or profile picture.
Graph Theory Concepts
Nodes: Each user is represented as a node.
Edges: Friendships are represented by edges connecting nodes.
Degree of a Node: The number of friendships a user has is equivalent to the degree of the corresponding node.
Mutual Friends: Users who share common friends (i.e., common edges).
Friends of Friends: Friend recommendations based on mutual friends (2-step connections).
Flow and Operations
User Registration and Authentication:

Users can sign up with a unique username, email, and password.
After signing up, users can log in using their credentials.
User Interactions:

Once logged in, users can access their profile, manage friendships (add/remove), view mutual friends, and create posts.
A queue-based system ensures posts are displayed in the order they were made (First-Come, First-Served).
Friend Recommendations:

The system suggests friends-of-friends by analyzing shared connections in the graph, helping users expand their social circle.
Database (Optional)
MySQL can be used to store user data, such as:
Users: Store user credentials, profile details, and friendships.
Posts: Store user-generated content.
Friendships: Track relationships as edges in the graph.
