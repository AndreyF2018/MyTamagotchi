package models;

import java.time.LocalDateTime;

public class Tamagotchi {

    protected static int maxAge;
    protected static int maxMood;
    protected static int maxHealth;

    protected static int minAge;
    protected static int minMood;
    protected static int minHealth;

    protected String name;
    protected int age;
    protected int mood;
    protected int health;
    protected LocalDateTime creationDate;
    protected LocalDateTime lastFeedingDate;
    protected LocalDateTime dethDate;
    protected String feed;

    // Method for feeding pet
    public void toFeed(LocalDateTime lastFeedingDate) {
        this.setLastFeedingDate(lastFeedingDate);
        this.setMood(maxMood);
        this.setHealth(maxHealth);
    }

    public void delete()
    {
        this.setCreationDate(null);
        this.setLastFeedingDate(null);
        this.setDethDate(LocalDateTime.now());
    }

    protected final void setMinParameters(){
        this.setMinAge(0);
        this.setMinMood(1);
        this.setMinHealth(1);
    }

    // Constructor for creating tamagotchi
    protected Tamagotchi (String name, LocalDateTime creationDate) {
        setMinParameters();
        this.setName(name);
        this.setCreationDate(creationDate);
        this.setLastFeedingDate(creationDate);
        this.setDethDate(null);

    }

    // Constructor for getting Tamagotchi
    protected Tamagotchi (String name, int age, int mood, int health, LocalDateTime creationDate, LocalDateTime lastFeedingDate, LocalDateTime dethDate) {
        setMinParameters();
        this.setName(name);
        this.setAge(age);
        this.setMood(mood);
        this.setHealth(health);
        this.setCreationDate(creationDate);
        this.setLastFeedingDate(lastFeedingDate);
        this.setDethDate(dethDate);
    }

    public int getMaxAge() {
        return maxAge;
    }

    protected void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxMood() {
        return maxMood;
    }

    protected void setMaxMood(int maxMood) {
        this.maxMood = maxMood;
    }

    public  int getMaxHealth() {
        return maxHealth;
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public  int getMinAge() {
        return minAge;
    }

    protected void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMinMood() {
        return minMood;
    }

    protected void setMinMood(int minMood) {
        this.minMood = minMood;
    }

    public int getMinHealth() {
        return minHealth;
    }

    protected void setMinHealth(int minHealth) {
        this.minHealth = minHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastFeedingDate() {
        return lastFeedingDate;
    }

    public void setLastFeedingDate(LocalDateTime lastFeedingDate) {
        this.lastFeedingDate = lastFeedingDate;
    }

    public LocalDateTime getDethDate() {
        return dethDate;
    }

    public void setDethDate(LocalDateTime dethDate) {
        this.dethDate = dethDate;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "Tamagotchi{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mood=" + mood +
                ", health=" + health +
                ", creationDate=" + creationDate +
                ", lastFeedingDate=" + lastFeedingDate +
                ", dethDate=" + dethDate +
                ", feed='" + feed + '\'' +
                '}';
    }
}
