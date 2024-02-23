package com.ableytner.bidtoname.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import com.ableytner.bidtoname.BlockIdtoName;

public class SaveCommand extends CommandBase implements ICommand {

    @Override
    public String getCommandName() {
        return "saveblockids";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "save all blockids and variations with their corresponding name to the 'blockid_to_name.json' file.";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft.getMinecraft().thePlayer
            .addChatMessage(new ChatComponentText("Saving blockid mappings, this may take a while..."));
        BlockIdtoName.generateList();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Saved to 'blockid_to_name.json'"));
    }
}
