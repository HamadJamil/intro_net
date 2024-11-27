# **IntroNet - Social Network Friend Recommendation System**

## 🚀 Project Overview

**IntroNet** is a social network application that leverages **graph theory** to model user relationships and provide insightful social recommendations. Each user is represented as a **node** in a graph, with friendships represented as **edges** connecting these nodes. The system offers several key functionalities such as mutual friends, friend recommendations, and a real-time user feed, similar to features commonly found on popular social media platforms.

## 📌 Key Features

- **Add Friends**: Allows users to create new friendships within the network.
- **Delete Friends**: Lets users break existing friendships.
- **Display Mutual Friends**: Helps users find mutual friends they share with another user.
- **Suggest Potential Friends**: Recommends friends based on common friends-of-friends.
- **User Posting System**: Lets users create posts that are stored and displayed in a **First-Come, First-Served (FCFS)** manner, simulating a real-time activity feed.

## ⚙️ Technologies Used

- **JavaFX**: Used for creating the graphical user interface (GUI).
- **IntelliJ IDEA**: The integrated development environment (IDE) used for development.
- **Java**: The primary programming language for the application logic.
- **MySQL**: Persistent data storage (e.g., user details, friendships, and posts).
- **GitHub**: Used for version control and project management.

## 📱 Pages & Features

### 1. **Login Page**

- **Fields**:
  - Email
  - Password
- **Buttons**:
  - **Sign In**
  - **Forgot Password?**
- **Transitions**:
  - Transition to the **Sign Up** page.

### 2. **Sign Up Page**

- **Fields**:
  - Username
  - Email
  - Password
- **Buttons**:
  - **Sign Up**
  - Transition to the **Sign In** page.

### 3. **User Profile Page**

- **Components**:
  - **Profile Picture(Optional)**
  - **Username**
  - **Friend Request Button**: To add a friend.
  - **Picture Feed**: Displays a user's posts in chronological order.

## 🧑‍💻 User Class

- **Attributes**:
  - `Picture`: User's profile picture.(Optional)
  - `Name`: User's name.
  - `Email`: User's email.
  - `Password`: User's password.
  - `Friend List`: List of friends (edges in the graph).
  - `Like List`: List of Likes.
  - `Comment List`: List of Comments.
  - `Posts Queue`: A queue to store user posts, displayed in FCFS order.

- **User Actions**:
  - **Add Friends**: Send friend requests and establish friendships.
  - **Remove Friends**: Unfriend someone.
  - **Make Posts**: Create and optionally edit posts. Posts are displayed in FCFS order.
  - **Update Personal Information**: Users can update their profile details like name, email, or profile picture.

## 🔗 Graph Theory Concepts

- **Nodes**: Each user is represented as a node.
- **Edges**: Friendships are represented by edges connecting nodes.
- **Degree of a Node**: The number of friendships a user has is equivalent to the degree of the corresponding node.
- **Mutual Friends**: Users who share common friends (i.e., common edges).
- **Friends of Friends**: Friend recommendations based on mutual friends (2-step connections).

## 🔄 Flow and Operations

### 1. **User Registration and Authentication**:

- Users can sign up with a unique **username**, **email**, and **password**.
- After signing up, users can log in using their credentials.

### 2. **User Interactions**:

Once logged in, users can:
- Access their **profile**.
- Manage **friendships** (add/remove).
- View **mutual friends** with other users.
- Create **posts** that are displayed in a **queue-based system** (First-Come, First-Served).

### 3. **Friend Recommendations**:

- The system suggests **friends-of-friends** based on shared connections in the graph, helping users expand their social circles.

## 🗃️ Database

**MySQL** can be used to store user data, including:
- **Users**: Store user credentials, profile details, and friendships.
- **Posts**: Store user-generated content.
- **Friendships**: Track relationships as edges in the graph.
