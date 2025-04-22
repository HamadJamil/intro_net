package Controller;


import Backend.DataBase;
import Model.Graph;

import java.util.*;

public class RecommendedFriend {
    Graph graph;
    DataBase database;
    public RecommendedFriend() {
       graph = new Graph();
      database = new DataBase();

    }


    public List<Integer> getFriendsOfFriends(int userId) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> directFriends = new HashSet<>(graph.getGraph().getOrDefault(userId, Collections.emptySet()));
        Set<Integer> friendsOfFriends = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(userId);
        visited.add(userId);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.getGraph().getOrDefault(current, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);

                    // Only add non-direct friends to the suggestion list
                    if (!directFriends.contains(neighbor) && neighbor != userId) {
                        friendsOfFriends.add(neighbor);
                    }
                }
            }
        }

        return friendsOfFriends.isEmpty() ? Collections.emptyList() : new ArrayList<>(friendsOfFriends);
    }

    public List<Integer> getFriendsOfFriend(int userId) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> directFriends = new HashSet<>(graph.getGraph().getOrDefault(userId, Collections.emptySet()));
        Set<Integer> recommendedFriends = new HashSet<>();

        // Visit only direct friends
        for (int directFriend : directFriends) {
            Set<Integer> friendsOfDirectFriend = graph.getGraph().getOrDefault(directFriend, Collections.emptySet());

            for (int friendOfFriend : friendsOfDirectFriend) {
                // Recommend only users who are not direct friends and not the original user
                if (friendOfFriend != userId && !directFriends.contains(friendOfFriend)) {
                    recommendedFriends.add(friendOfFriend);
                }
            }
        }
        return new ArrayList<>(recommendedFriends);
    }


    public static void main(String[] args) {
        RecommendedFriend recommendedFriend = new RecommendedFriend();
        List<Integer> friendsOfFriends = recommendedFriend.getFriendsOfFriends(8);
        System.out.println(friendsOfFriends);
    }
}
