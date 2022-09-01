package org.kayteam.taskapi.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class Task{

    private final JavaPlugin javaPlugin;
    private BukkitTask bukkitTask;
    private final long ticks;

    public Task(JavaPlugin javaPlugin, long ticks){

        this.javaPlugin = javaPlugin;

        this.ticks = ticks;

    }

    /**
     * Start the task.
     */
    public void startTask() {

        preStartActions();

        BukkitScheduler scheduler = javaPlugin.getServer().getScheduler();

        bukkitTask = scheduler.runTaskTimer(javaPlugin, this::actions, 0L, ticks);

        postStartActions();

    }

    /**
     * Verify if the task is running.
     */
    public boolean isRunning() {

        return javaPlugin.getServer().getScheduler().isCurrentlyRunning(bukkitTask.getTaskId());

    }

    /**
     * Stop the task.
     */
    public void stopTask(){

        preStopActions();

        BukkitScheduler scheduler = javaPlugin.getServer().getScheduler();

        scheduler.cancelTask(bukkitTask.getTaskId());

        postStopActions();

    }

    public abstract void actions();

    /**
     * Executed before starting the task
     */
    public void preStartActions() {}

    /**
     * Executed after starting the task
     */
    public void postStartActions() {}

    /**
     * Executed before stopping the task
     */
    public void preStopActions() {}

    /**
     * Executed after stopping the task
     */
    public void postStopActions() {}

}