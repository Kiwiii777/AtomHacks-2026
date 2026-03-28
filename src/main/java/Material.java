public class Material {
    private String name;
    private int value;
    private double amount;

    public Material(String name, int value, double amount) {
        this.name = name;
        this.value = value;
        this.amount = amount;
    }

    public String getName()   { return name; }
    public int getValue()     { return value; }
    public double getAmount() { return amount; }

    // Registry
    public static final Material IRON         = new Material("IRON",         10,     5.0);
    public static final Material GOLD         = new Material("GOLD",         50,     2.5);
    public static final Material DIAMOND      = new Material("DIAMOND",      200,    1.0);

    public static final Material MARTIAN_ROCK = new Material("MARTIAN ROCK", 1000,   0.5);
    public static final Material HEMATITE     = new Material("HEMATITE",     5000,   0.1);
    public static final Material POLAR_ICE    = new Material("POLAR ICE",    7500,   0.01);

    public static final Material STORM_STONE  = new Material("STORM STONE",  15000,  0.005);
    public static final Material AEROGEL      = new Material("AEROGEL",      50000,  0.001);
    public static final Material SOLID_HELIUM = new Material("SOLID HELIUM", 100000, 0.0001);

    public static final Material SILICATE     = new Material("SILICATE",     300,    3.0);
    public static final Material NICKEL       = new Material("NICKEL",       800,    1.5);
    public static final Material PLATINUM     = new Material("PLATINUM",     12000,  0.08);

    public static final Material METHANE_ICE  = new Material("METHANE ICE",  20000,  0.003);
    public static final Material RING_DUST    = new Material("RING DUST",    8000,   0.02);
    public static final Material AMMONIA_SHARD= new Material("AMMONIA SHARD",35000,  0.002);

    public static final Material DARK_MATTER  = new Material("DARK MATTER",  500000, 0.00005);
    public static final Material TRITIUM      = new Material("TRITIUM",      250000, 0.0001);
}