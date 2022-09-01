package task;

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

    public void startScheduler() {
        BukkitScheduler scheduler = javaPlugin.getServer().getScheduler();
        bukkitTask = scheduler.runTaskTimer(javaPlugin, this::actions, 0L, ticks);
    }

    public void stopScheduler(){
        BukkitScheduler scheduler = javaPlugin.getServer().getScheduler();
        scheduler.cancelTask(bukkitTask.getTaskId());
        stopActions();
    }

    public abstract void actions();

    public void stopActions(){}

    public boolean isRunning() {
        return javaPlugin.getServer().getScheduler().isCurrentlyRunning(bukkitTask.getTaskId());
    }

}