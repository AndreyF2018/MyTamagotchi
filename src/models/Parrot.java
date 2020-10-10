package models;

import java.time.LocalDateTime;

public class Parrot extends Tamagotchi {

    private final void setMaxParameters() {
        this.setMaxAge(6); // in days
        this.setMaxMood(130); // in minutes
        this.setMaxHealth(60); // in minutes
    }

    // Constructor for creating
    public Parrot(String name, LocalDateTime creationDate) {
        super(name, creationDate);
        setMaxParameters();
        this.setAge(this.getMinAge());
        this.setMood(this.getMaxMood());
        this.setHealth(this.getMaxHealth());
        this.setFeed("Prestige");
    }

    // Constructor for getting
    public Parrot(String name, int age, int mood, int health, LocalDateTime creationDate, LocalDateTime lastFeedingDate, LocalDateTime dethDate ){
        super(name, age, mood, health, creationDate, lastFeedingDate, dethDate);
        setMaxParameters();
        this.setFeed("Prestige");
    }

    @Override
    protected final void setMaxAge(int maxAge) {
        super.setMaxAge(maxAge);
    }

    @Override
    protected final void setMaxMood(int maxMood) {
        super.setMaxMood(maxMood);
    }

    @Override
    protected final void setMaxHealth(int maxHealth) {
        super.setMaxHealth(maxHealth);
    }

    @Override
    protected final void setMinAge(int minAge) {
        super.setMinAge(minAge);
    }

    @Override
    protected final void setMinMood(int minMood) {
        super.setMinMood(minMood);
    }

    @Override
    protected final void setMinHealth(int minHealth) {
        super.setMinHealth(minHealth);
    }
}
