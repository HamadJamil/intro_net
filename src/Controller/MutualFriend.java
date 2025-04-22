package Controller;


import Model.Graph;

import java.util.List;


public class MutualFriend {

    public static void main(String[] args) {
        Graph graph = new Graph();
        List<String> mutualFriends = graph.getMutualFriends(1, 2);
        System.out.println(mutualFriends.toString());

    }
}
