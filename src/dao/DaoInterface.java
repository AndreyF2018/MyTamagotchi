package dao;

import com.github.cliftonlabs.json_simple.JsonException;
import models.Tamagotchi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDateTime;

// Interface describing methods for interacting with data from a json file
public interface DaoInterface {
    public <T extends Tamagotchi> void createTama(String className, String name, LocalDateTime creationDate) throws IOException, JsonException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    public <T extends Tamagotchi> T getTama() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException, JsonException;
    public boolean isTamaExists() throws IOException, JsonException;
    public Duration getTimeAfterDeath() throws IOException, JsonException;
    public boolean isPossibleCreateTama() throws IOException, JsonException;
    public boolean isTamaDied() throws IOException, JsonException;
    public <T extends Tamagotchi> void updateTamaState (String className, T tamagotchi) throws IOException;
    public void deleteTama () throws IOException;

}
