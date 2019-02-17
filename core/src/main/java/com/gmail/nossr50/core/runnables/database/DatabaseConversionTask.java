package com.gmail.nossr50.core.runnables.database;

import com.gmail.nossr50.core.data.database.DatabaseManager;
import com.gmail.nossr50.core.locale.LocaleLoader;
import com.gmail.nossr50.core.mcmmo.commands.CommandSender;

public class DatabaseConversionTask extends BukkitRunnable {
    private final DatabaseManager sourceDatabase;
    private final CommandSender sender;
    private final String message;

    public DatabaseConversionTask(DatabaseManager sourceDatabase, CommandSender sender, String oldType, String newType) {
        this.sourceDatabase = sourceDatabase;
        this.sender = sender;
        message = LocaleLoader.getString("Commands.mcconvert.Database.Finish", oldType, newType);
    }

    @Override
    public void run() {
        sourceDatabase.convertUsers(mcMMO.getDatabaseManager());

        mcMMO.p.getServer().getScheduler().runTask(mcMMO.p, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(message);
            }
        });
    }
}