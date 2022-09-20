package com.tangerine.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandRegistration extends ListenerAdapter {
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        OptionData voice = new OptionData(OptionType.CHANNEL, "voice", "try a channel", true).setChannelTypes(ChannelType.VOICE);
        OptionData user = new OptionData(OptionType.USER, "user", "mention the user you want", true);
        SubcommandData mute = new SubcommandData("mute", "mutes the user in the voice").addOptions(user);
        SubcommandData deafen = new SubcommandData("deafen", "deafens the user in the voice").addOptions(user);
        SubcommandData unMute = new SubcommandData("unmute", "UnMutes the user in the voice").addOptions(user);
        SubcommandData unDeafen = new SubcommandData("undeafen", "UnDeafen the user in the voice").addOptions(user);
        SubcommandData move = new SubcommandData("move", "moves the user").addOptions(user, voice);
        commandData.add(Commands.slash("voice", "voice controlling")
                .addSubcommands(mute, deafen, move, unMute, unDeafen)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.VOICE_MOVE_OTHERS, Permission.VOICE_DEAF_OTHERS, Permission.VOICE_MUTE_OTHERS)));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
