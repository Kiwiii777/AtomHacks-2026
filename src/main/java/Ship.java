public class Ship {

    // Tiers
    public static final String TIER_SCOUT = "Scout";
    public static final String TIER_FRIGATE = "Frigate";
    public static final String TIER_CRUISER = "Cruiser";
    public static final String TIER_MOTHERSHIP = "Mothership";

    private String id, name, brand, tier;
    private int thrust, speed, capacity;
    private double miningSpeed, miningMultiplier;

    private int baseCost;

    public Ship(String id, String name, String brand, String tier,
                int thrust, int speed, int capacity,
                double miningSpeed, double miningMultiplier, int baseCost) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.tier = tier;
        this.thrust = thrust;
        this.speed = speed;
        this.capacity = capacity;
        this.miningSpeed = miningSpeed;
        this.miningMultiplier = miningMultiplier;
        this.baseCost = baseCost;
    }

    public String getId() { 
        return id; 
    }
    public String getName() {
        return name; 
    }
    public String getBrand() {
        return brand; 
    }
    public String getTier() { 
        return tier;
    }
    public int getThrust() {
        return thrust;
    }
    public int getSpeed() {
        return speed;
    }
    public int getCapacity() {
        return capacity;
    }
    public double getMiningSpeed() {
        return miningSpeed;
    }
    public double getMiningMultiplier() {
        return miningMultiplier;
    }
    public int getBaseCost() {
        return baseCost;
    }

    public static int upgradeCost(String attr, int currentLevel) {
        int base;
        switch (attr) {
            case "speed":
                base = 500;
                break;
            case "thrust":
                base = 400;
                break;
            case "capacity":
                base = 600;
                break;
            case "miningSpeed":
                base = 800;
                break;
            case "miningMultiplier":
                base = 1200;
                break;
            default:
                base = 500;
        }
        return base * currentLevel;
    }

    public static Ship[] getCatalog() {
        return new Ship[]{
            // ─ Scouts (YV brand) ─
            new Ship("YV1", "YV-1 Sparrow","YV", TIER_SCOUT,12, 3,  400,  0.5, 1.0,  800),
            new Ship("YV2", "YV-2 Falcon","YV", TIER_SCOUT,14, 4,  600,  0.8, 1.1, 1500),
            new Ship("YV3", "YV-3 Hawk","YV", TIER_SCOUT,16, 5,  800,  1.2, 1.2, 2800),

            // ─ Scouts (XV brand) ─
            new Ship("XV12","XV-12 Dart","XV", TIER_SCOUT,8, 5,  100,  0.6, 1.0,  900),
            new Ship("XV13","XV-13 Arrow","XV", TIER_SCOUT,10, 6,  120,  1.0, 1.15,1800),
            new Ship("XV14","XV-14 Bolt","XV", TIER_SCOUT,15, 8,   80,  1.5, 1.3, 3500),

            // ─ Frigates ─
            new Ship("FR1", "FR-1 Corsair","FR", TIER_FRIGATE,20, 4, 2000,  2.0, 1.5,  8000),
            new Ship("FR2", "FR-2 Brigand","FR", TIER_FRIGATE,25, 5, 3000,  3.0, 1.7, 15000),

            // ─ Cruisers ─
            new Ship("CR1", "CR-1 Titan","CR", TIER_CRUISER,40, 3, 8000,  5.0, 2.0, 50000),
            new Ship("CR2", "CR-2 Colossus","CR", TIER_CRUISER,55, 2,15000,  8.0, 2.5,120000),

            new Ship("MS1", "Leviathan-I", "MS", TIER_MOTHERSHIP,100,1,999999,0.0, 1.0,0),
        };
    }
}