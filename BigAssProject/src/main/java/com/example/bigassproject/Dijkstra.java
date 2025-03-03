package com.example.bigassproject;
import java.util.*;

public class Dijkstra{
    public static void main(String[] args){
        Knoten A = new Knoten("A");
        Knoten B = new Knoten("B");
        Knoten C = new Knoten("C");
        Knoten D = new Knoten("D");
        Knoten E = new Knoten("E");
        Knoten F = new Knoten("F");
        Knoten G = new Knoten("G");
        A.addnachfolger(new edge(B, 4));
        A.addnachfolger(new edge(C, 10));
        B.addnachfolger(new edge(E,3));
        B.addnachfolger(new edge(D,15));
        C.addnachfolger(new edge(D,1));
        D.addnachfolger(new edge(E,7));
        D.addnachfolger(new edge(G,5));
        E.addnachfolger(new edge(F, 10));
        E.addnachfolger(new edge(G, 6));

        ArrayList<Knoten> graph = new ArrayList<Knoten>();
        graph.add(A);
        graph.add(B);
        graph.add(C);
        graph.add(D);
        graph.add(E);
        graph.add(F);
        graph.add(G);

        Dijkstra dijkstra = new Dijkstra();

        A.setdistance(0);

        int dist = dijkstra.shortestDistance(A, G, graph);

        
        ArrayList<Knoten> pfad = dijkstra.shortestPath(A, G);
        System.out.println(dist);
        for(int i = 0; i < pfad.size(); i++){
            System.out.println(pfad.get(i));
        }
        

    }
    public ArrayList<Knoten> shortestPath(Knoten source, Knoten position){
        ArrayList<Knoten> pfad = new ArrayList<Knoten>();
        return shortestPath(source, position, pfad);
    }
    public ArrayList<Knoten> shortestPath(Knoten source, Knoten position, ArrayList<Knoten> pfad){
        if(position == source){
            pfad.addFirst(position);
            return pfad;
        }
        pfad.addFirst(position);
        return shortestPath(source, position.getvorgänger(), pfad);
    }
    public int shortestDistance(Knoten source, Knoten finalDestination, ArrayList<Knoten> graph){
        source.setExplored(true);
        if(source == finalDestination){
            return source.getdistance();
        }
        for(int i = 0; i < source.getNachfolger().size(); i++){
            Knoten destination = source.getNachfolger().get(i).destination;
            if (!destination.getExplored()){
                if(destination.getdistance() > source.getdistance() + source.getNachfolger().get(i).weigth){
                    destination.setdistance(source.getdistance() + source.getNachfolger().get(i).weigth);
                    destination.setvorgänger(source);
                }
            }
        }
        Knoten nextSource = new Knoten("nextSource");
        for(int i = 0; i < graph.size(); i++){
            if(!graph.get(i).getExplored() && graph.get(i).getdistance() < nextSource.getdistance()){
                nextSource = graph.get(i);
            }
        }
        return shortestDistance(nextSource, finalDestination, graph);
    }
}
class Knoten {
    private String name;
    private int distance = Integer.MAX_VALUE;
    private Knoten vorgänger;
    private ArrayList<edge> nachfolger = new ArrayList<edge>();
    private Boolean explored = false;

    public Knoten(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setExplored(boolean bool){
        this.explored = bool;
    }
    public boolean getExplored(){
        return this.explored;
    }

    public void addnachfolger(edge e) {
        nachfolger.add(e);
    }

    public ArrayList<edge> getNachfolger(){
        return this.nachfolger;
    }

    public void setdistance(int distance) {
        this.distance = distance;
    }

    public int getdistance() {
        return distance;
    }

    public Knoten getvorgänger() {
        return vorgänger;
    }

    public void setvorgänger(Knoten vorgänger) {
        this.vorgänger = vorgänger;
    }
}
class edge{
    Knoten destination;
    int weigth;
    public edge(Knoten destination, int weigth){
        this.destination = destination;
        this.weigth = weigth;
    }
}


