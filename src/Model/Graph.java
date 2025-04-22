package Model;

import Backend.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Graph {

    public static HashMap<Integer, Set<Integer>> graph = new HashMap<>();
    DataBase database;

    public Graph() {
        database = new DataBase();
        loadGraph2();
        toString();
    }

    public HashMap<Integer, Set<Integer>> getGraph() {
        return graph;
    }


    public static void printGraph() {
        for (int i : graph.keySet()) {
            System.out.print(i + " -> ");
            for (int j : graph.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public Set<Integer> getFriends(int userId) {
        return graph.getOrDefault(userId, new HashSet<>());
    }

    void loadGraph2() {
        String sql = "Select * from friends";
        try {
            ResultSet rs = database.getStatement().executeQuery(sql);
            while (rs.next()) {
                int user1 = rs.getInt("User");
                int user2 = rs.getInt("Friend");
                graph.computeIfAbsent(user1, k -> new HashSet<>()).add(user2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMutualFriends(int user1, int user2) {
        // friend of user 1
        Set<Integer> friendsOfUser1 = getFriends(user1);
        System.out.println(friendsOfUser1 + " are friends of 1");
        // friend of user 2
        Set<Integer> friendsOfUser2 = getFriends(user2);
        System.out.println(friendsOfUser2 + "are friends of 2");
        // built in method for intersection
        friendsOfUser1.retainAll(friendsOfUser2);

        List<String> mutualFriends = new ArrayList<>();
        for (Integer friendId : friendsOfUser1) {
            String username = getUsernameById(friendId);
            mutualFriends.add(username);
        }

        return mutualFriends;
    }

    public String getUsernameById(int friendId) {
        String sql = "select FirstName , LastName from users where id = " + friendId;
        try {
            ResultSet rs = database.getStatement().executeQuery(sql);
            if (rs.next()) {
                return rs.getString("FirstName") + " " + rs.getString("LastName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        printGraph();
    }

    //1 -> 2 3 4 5 6
    //2 -> 1 3 5 6
    //3 -> 1 2 5
    //4 -> 1 5 7
    //5 -> 1 2 3 4
    //6 -> 1 2 7
    //7 -> 4 6
}
