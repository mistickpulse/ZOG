package ZEventManager.Commands;

import ZEventManager.ZEventManager;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    public void sendHelp(CommandSender s) {
        String helper = ChatColor.BLUE + "Usage : /ZEvent";
        s.sendMessage(helper);
    }

    //Ajouter /ZEvent desc (Appel au plugin->getCurrentEvent(); pour chopper L'event
    // Ensuite chopper son type et dump la description qui correspond dans le fichier de conf (plugin.yml)

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ZEvent")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!player.hasPermission("ZEvent.*")) {
                    player.sendMessage(ChatColor.RED + "You are not allowed to perform this command");
                }
                if (args.length == 0) {
                    sendHelp(sender);
                    return true;
                }
                __handleCommand(sender, command, label, args);
            }
            return true;
        }
        this.sendHelp(sender);
        return false;
    }


    //Private
    private void __handleCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "clearChat":
               for (int i = 0; i < 50; ++i) {
                   sender.sendMessage("");
               }
               break;
            case "gen":
                __eventInstanciator(sender, command, label, args);
                break;
            case "create":
                if (args.length == 2)
                System.out.print("Creating Event");
                Bukkit.broadcastMessage("Creating event");
                ZEventManager tmp = (ZEventManager)Bukkit.getPluginManager().getPlugin("ZEvent");
                tmp.createEvent(args[1]);
                break;
        }
    }

    private void __eventInstanciator(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.RED + "Liste des mode de jeu:");
        String Format3 = "=====>";
        TextComponent FillBucketMode = new TextComponent("");
        TextComponent PickupItem = new TextComponent("");
        TextComponent KillEvent = new TextComponent("");
        TextComponent clearChat = new TextComponent("");
        TextComponent emptyLine = new TextComponent("");

        clearChat.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ZEvent clearChat"));

        {
            TextComponent data = new TextComponent(ChatColor.GRAY + Format3 + ChatColor.GREEN + "Bucket Filling");
            data.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ZEvent create FillBucket"));
            data.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Create a FillBucketEvent").create()));
            FillBucketMode.addExtra(data);
            FillBucketMode.addExtra(clearChat);
        }
        {
            TextComponent data = new TextComponent(ChatColor.GRAY + Format3 + ChatColor.GREEN + "Item Dropping");
            data.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ZEvent create DropItem"));
            data.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Create a DropItem Event").create()));
            PickupItem.addExtra(data);
            PickupItem.addExtra(clearChat);
        }
        {
            TextComponent data = new TextComponent(ChatColor.GRAY + Format3 + ChatColor.GREEN + "Player Killing");
            data.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ZEvent create KillEvent"));
            data.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Create a Pvp Event").create()));
            KillEvent.addExtra(data);
            KillEvent.addExtra(clearChat);
        }

        TextComponent format1 = new TextComponent(ChatColor.RED + "ZOG Offer you some events, please pick one");
        TextComponent format2 = new TextComponent(ChatColor.BLUE +"=====================================");
        TextComponent format3 = new TextComponent(ChatColor.GRAY + "====>");


        for (int i = 0; i < 15; ++i) {
            ((Player)sender).spigot().sendMessage(emptyLine);
        }
        ((Player)sender).spigot().sendMessage(format1);
        ((Player)sender).spigot().sendMessage(format2);

        ((Player)sender).spigot().sendMessage(emptyLine);
        ((Player)sender).spigot().sendMessage(FillBucketMode);
        ((Player)sender).spigot().sendMessage(emptyLine);
        ((Player)sender).spigot().sendMessage(PickupItem);
        ((Player)sender).spigot().sendMessage(emptyLine);
        ((Player)sender).spigot().sendMessage(KillEvent);
    }

    //Commands
    private void __commandModify(CommandSender sender, Command command, String label, String[] args) {
    }
}
