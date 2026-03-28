import java.util.ArrayList;
import java.util.List;

public class Planet {

    private String name;
    private double distance; // in AU
    private Material[] materials;

    public Planet(String name, double distance, Material[] materials) {
        this.name = name;
        this.distance = distance;
        this.materials = materials;
    }

    public String getName()       { return name; }
    public double getDistance()   { return distance; }
    public Material[] getMaterials() { return materials; }

    public static List<Planet> getPlanets() {
        List<Planet> list = new ArrayList<>();

        list.add(new Planet("Mercury", 0.39, new Material[]{
            Material.SILICATE,
            Material.IRON,
            Material.NICKEL
        }));

        list.add(new Planet("Venus", 0.72, new Material[]{
            Material.SILICATE,
            Material.GOLD,
            Material.PLATINUM
        }));

        list.add(new Planet("Earth", 1.0, new Material[]{
            Material.IRON,
            Material.GOLD,
            Material.DIAMOND
        }));

        list.add(new Planet("Mars", 1.52, new Material[]{
            Material.MARTIAN_ROCK,
            Material.HEMATITE,
            Material.POLAR_ICE
        }));

        list.add(new Planet("Asteroid Belt", 2.7, new Material[]{
            Material.NICKEL,
            Material.PLATINUM,
            Material.SILICATE
        }));

        list.add(new Planet("Jupiter", 5.2, new Material[]{
            Material.STORM_STONE,
            Material.AEROGEL,
            Material.SOLID_HELIUM
        }));

        list.add(new Planet("Saturn", 9.58, new Material[]{
            Material.RING_DUST,
            Material.METHANE_ICE,
            Material.AMMONIA_SHARD
        }));

        list.add(new Planet("Uranus", 19.2, new Material[]{
            Material.METHANE_ICE,
            Material.AMMONIA_SHARD,
            Material.TRITIUM
        }));

        list.add(new Planet("Neptune", 30.05, new Material[]{
            Material.TRITIUM,
            Material.DARK_MATTER,
            Material.SOLID_HELIUM
        }));

        return list;
    }
}