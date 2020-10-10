package dao;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import models.Tamagotchi;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;


public class DaoTama implements DaoInterface {

    @Override
    public boolean isTamaExists() throws IOException, JsonException {
        Reader reader = Files.newBufferedReader(Paths.get("tamagotchiState.json"));
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        if (parser.get("creation_date") == null) {
            reader.close();
            return false;

        }
        else {
            reader.close();
            return true;
        }
    }
    @Override
    public boolean isTamaDied() throws IOException, JsonException {
        Reader reader = Files.newBufferedReader(Paths.get("tamagotchiState.json"));
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        if (parser.get("death_date") == null) {
            reader.close();
            return false;
        }
        else {
            reader.close();
            return true;
        }

    }

    @Override
    public Duration getTimeAfterDeath() throws IOException, JsonException {
        Duration timeAfterDeath = null;
        if (isTamaDied()) {
            Reader reader = Files.newBufferedReader(Paths.get("tamagotchiState.json"));
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
            LocalDateTime deathDate = LocalDateTime.parse((CharSequence) parser.get("death_date"));
            LocalDateTime currentDate = LocalDateTime.now();
            timeAfterDeath = Duration.between(deathDate, currentDate);
            reader.close();

        }
        return timeAfterDeath;
    }
    @Override
    public boolean isPossibleCreateTama() throws IOException, JsonException {
        if (isTamaDied()) {
            if (isTamaExists()) {
                return false;
                // If less than 1 hour has passed since the death of pet
            } else if (getTimeAfterDeath().toHours() < 1) {
                //throw new NullPointerException("You can create a pet only 1 hour after the previous one left");
                return false;

            } else {
                return true;
            }
        }
        else {
            return !isTamaExists();
        }
    }
       @Override
    public <T extends Tamagotchi> void createTama (String className, String name, LocalDateTime creationDate) throws IOException, JsonException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (isPossibleCreateTama()){
            Writer writer = Files.newBufferedWriter(Paths.get("tamagotchiState.json"));
            Class clazz = Class.forName("models." + className);
            Class [] params = {String.class, LocalDateTime.class};
            T pet = (T) clazz.getConstructor(params).newInstance(name, LocalDateTime.now());
            Tamagotchi tamagotchi = pet;
            JsonObject jsonTama = new JsonObject();
            jsonTama.put("Type", className);
            jsonTama.put("maxAge", tamagotchi.getMaxAge());
            jsonTama.put("maxMood", tamagotchi.getMaxMood());
            jsonTama.put("maxHealth", tamagotchi.getMaxHealth());
            jsonTama.put("minAge", tamagotchi.getMinAge());
            jsonTama.put("minMood", tamagotchi.getMinMood());
            jsonTama.put("minHealth", tamagotchi.getMinHealth());
            jsonTama.put("name", tamagotchi.getName());
            jsonTama.put("age", tamagotchi.getAge());
            jsonTama.put("mood", tamagotchi.getMood());
            jsonTama.put("health", tamagotchi.getHealth());
            creationDate = LocalDateTime.now();
            jsonTama.put("creation_date", creationDate.toString());
            jsonTama.put("last_feeding_date", creationDate.toString());
            jsonTama.put("death_date", null);
            jsonTama.put("feed", tamagotchi.getFeed());
            Jsoner.serialize(jsonTama, writer);
            writer.close();
        }
        else {
            //throw new NullPointerException("You have already created a character");
            //System.out.println("You have already created a character from create Tama");

        }
    }

    @Override
    public void deleteTama() throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("tamagotchiState.json"));
        JsonObject jsonTama = new JsonObject();
        jsonTama.put("Type", null);
        jsonTama.put("maxAge", null);
        jsonTama.put("maxMood", null);
        jsonTama.put("maxHealth", null);
        jsonTama.put("minAge", null);
        jsonTama.put("minMood", null);
        jsonTama.put("minHealth", null);
        jsonTama.put("name", null);
        jsonTama.put("age", null);
        jsonTama.put("mood", null);
        jsonTama.put("health", null);
        jsonTama.put("creation_date", null);
        jsonTama.put("last_feeding_date", null);
        jsonTama.put("death_date", LocalDateTime.now().toString());
        jsonTama.put("feed", null);
        Jsoner.serialize(jsonTama, writer);
        writer.close();
    }

    @Override
    public <T extends Tamagotchi> void updateTamaState(String className, T tamagotchi) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("tamagotchiState.json"));
        JsonObject jsonTama = new JsonObject();
        jsonTama.put("Type", className);
        jsonTama.put("maxAge", tamagotchi.getMaxAge());
        jsonTama.put("maxMood", tamagotchi.getMaxMood());
        jsonTama.put("maxHealth", tamagotchi.getMaxHealth());
        jsonTama.put("minAge", tamagotchi.getMinAge());
        jsonTama.put("minMood", tamagotchi.getMinMood());
        jsonTama.put("minHealth", tamagotchi.getMinHealth());
        jsonTama.put("name", tamagotchi.getName());
        jsonTama.put("age", tamagotchi.getAge());
        jsonTama.put("mood", tamagotchi.getMood());
        jsonTama.put("health", tamagotchi.getHealth());
        if (tamagotchi.getCreationDate() == null){
            jsonTama.put("creation_date", null);
        }
        else {
            jsonTama.put("creation_date", tamagotchi.getCreationDate().toString());
        }
        if (tamagotchi.getLastFeedingDate() == null) {
            jsonTama.put("last_feeding_date", null);
        }
        else {
            jsonTama.put("last_feeding_date", tamagotchi.getLastFeedingDate().toString());
        }
        if (tamagotchi.getDethDate() == null)  {
            jsonTama.put("death_date", null);
        }
        else {
            jsonTama.put("death_date", tamagotchi.getDethDate().toString());
        }
        jsonTama.put("feed", tamagotchi.getFeed());
        Jsoner.serialize(jsonTama, writer);
        writer.close();

    }

    @Override
    public <T extends Tamagotchi> T getTama() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException, JsonException {

        T tamagotchi = null;
        String className = null;
        // if (isTamaExists() && !isTamaDied()) {
        Reader reader = Files.newBufferedReader(Paths.get("tamagotchiState.json"));
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        className = parser.get("Type").toString();
        String name = parser.get("name").toString();
        LocalDateTime creationDate = LocalDateTime.parse((CharSequence) parser.get("creation_date"));
        LocalDateTime lastFeedingDate = LocalDateTime.parse((CharSequence) parser.get("last_feeding_date"));
        LocalDateTime dethDate = null;
        BigDecimal maxAgeTemp = (BigDecimal) parser.get("maxAge");
        int maxAge = maxAgeTemp.intValue();
        BigDecimal maxMoodTemp = (BigDecimal) parser.get("maxMood");
        int maxMood = maxMoodTemp.intValue();
        BigDecimal maxHealthTemp = (BigDecimal) parser.get("maxHealth");
        int maxHealth = maxHealthTemp.intValue();
        BigDecimal minAgeTemp = (BigDecimal) parser.get("minAge");
        int minAge = minAgeTemp.intValue();
        BigDecimal minMoodTemp = (BigDecimal) parser.get("minMood");
        int minMood = minMoodTemp.intValue();
        BigDecimal minHealthTemp = (BigDecimal) parser.get("minHealth");
        int minHealth = minHealthTemp.intValue();

        // Age calculation:
        Duration duration = Duration.between(creationDate, LocalDateTime.now());
        int age = (int) duration.toDays();
        // Mood calculation:
        duration = Duration.between(lastFeedingDate, LocalDateTime.now());
        int mood = (int) (maxMood - duration.toMinutes());
        // Health calculation:
        int health = maxHealth;
        if (mood < minMood) {
            health = maxHealth - Math.abs(mood);
        }
        Class clazz = Class.forName("models." + className);
        Class[] params = {String.class, int.class, int.class, int.class, LocalDateTime.class, LocalDateTime.class, LocalDateTime.class};
        tamagotchi = (T) clazz.getConstructor(params).newInstance(name, age, mood, health, creationDate, lastFeedingDate, dethDate);
        reader.close();
        //}

        if (tamagotchi != null) {
            // Update state of the tamagotchi in JSON
            updateTamaState(className, tamagotchi);

        }
        return tamagotchi;
    }


}
