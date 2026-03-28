import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Spark;

public class Main {

    static Gson gson = new Gson();
    static long credits = 5000;

    static Map<String, OwnedShip> ownedShips = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Spark.port(4567);
        Spark.staticFiles.location("/public");

        Spark.before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type");
        });

        Spark.options("/*", (req, res) -> "OK");

        Spark.get("/api/planets", (req, res) -> {
            res.type("application/json");
            return gson.toJson(Planet.getPlanets());
        });

        Spark.get("/api/ships/catalog", (req, res) -> {
            res.type("application/json");
            return gson.toJson(Ship.getCatalog());
        });

        Spark.get("/api/state", (req, res) -> {
            res.type("application/json");

            JsonObject obj = new JsonObject();
            obj.addProperty("credits", credits);
            obj.add("ownedShips", gson.toJsonTree(ownedShips.values()));

            return obj.toString();
        });

        Spark.post("/api/ships/buy", (req, res) -> {
            res.type("application/json");

            JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();
            String shipId = body.get("shipId").getAsString();

            Ship template = findTemplate(shipId);
            if (template == null) return error("Unknown ship ID");

            if (credits < template.getBaseCost()) return error("Insufficient credits");

            credits -= template.getBaseCost();

            String ownedId = UUID.randomUUID().toString().substring(0, 8);
            ownedShips.put(ownedId, new OwnedShip(ownedId, template));

            JsonObject resp = new JsonObject();
            resp.addProperty("ok", true);
            resp.addProperty("credits", credits);
            resp.addProperty("ownedId", ownedId);

            return resp.toString();
        });

        Spark.post("/api/ships/assign", (req, res) -> {
            res.type("application/json");

            JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();
            String ownedId = body.get("ownedId").getAsString();
            String planet = body.has("planet") ? body.get("planet").getAsString() : null;

            OwnedShip ship = ownedShips.get(ownedId);
            if (ship == null) return error("Ship not found");

            ship.assignedPlanet = planet;

            JsonObject resp = new JsonObject();
            resp.addProperty("ok", true);

            return resp.toString();
        });

        Spark.post("/api/ships/upgrade", (req, res) -> {
            res.type("application/json");

            JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();
            String ownedId = body.get("ownedId").getAsString();
            String attr = body.get("attr").getAsString();

            OwnedShip ship = ownedShips.get(ownedId);
            if (ship == null) return error("Ship not found");

            int level = ship.upgradeLevels.getOrDefault(attr, 1);
            int cost = Ship.upgradeCost(attr, level);

            if (credits < cost) return error("Insufficient credits");

            credits -= cost;

            ship.applyUpgrade(attr);
            ship.upgradeLevels.put(attr, level + 1);

            JsonObject resp = new JsonObject();
            resp.addProperty("ok", true);
            resp.addProperty("credits", credits);
            resp.add("ship", gson.toJsonTree(ship));

            return resp.toString();
        });

        Spark.post("/api/deposit", (req, res) -> {
            res.type("application/json");

            JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();
            long amount = body.get("amount").getAsLong();

            credits += amount;

            JsonObject resp = new JsonObject();
            resp.addProperty("ok", true);
            resp.addProperty("credits", credits);

            return resp.toString();
        });

        // ── POST /api/credits/sync ────────────────────────────
        Spark.post("/api/credits/sync", (req, res) -> {
            res.type("application/json");

            JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();
            credits = body.get("credits").getAsLong();

            JsonObject resp = new JsonObject();
            resp.addProperty("ok", true);
            resp.addProperty("credits", credits);

            return resp.toString();
        });

        System.out.println("AtomHacks 2026 running on http://localhost:4567");
    }

    static Ship findTemplate(String id) {
        for (Ship s : Ship.getCatalog())
            if (s.getId().equals(id)) return s;
        return null;
    }

    static String error(String msg) {
        JsonObject o = new JsonObject();
        o.addProperty("ok", false);
        o.addProperty("error", msg);
        return o.toString();
    }

    static class OwnedShip {
        String id, templateId, name, brand, tier;
        int thrust, speed, capacity;
        double miningSpeed, miningMultiplier;
        String assignedPlanet;

        Map<String, Integer> upgradeLevels = new HashMap<>();

        OwnedShip(String id, Ship template) {
            this.id = id;
            this.templateId = template.getId();
            this.name = template.getName();
            this.brand = template.getBrand();
            this.tier = template.getTier();
            this.thrust = template.getThrust();
            this.speed = template.getSpeed();
            this.capacity = template.getCapacity();
            this.miningSpeed = template.getMiningSpeed();
            this.miningMultiplier = template.getMiningMultiplier();
            this.assignedPlanet = null;
        }

        void applyUpgrade(String attr) {
            switch (attr) {
                case "speed": speed += 1; break;
                case "thrust": thrust += 2; break;
                case "capacity": capacity += 200; break;
                case "miningSpeed": miningSpeed = +(miningSpeed + 0.3); break;
                case "miningMultiplier": miningMultiplier = +(miningMultiplier + 0.1); break;
            }
        }
    }
}