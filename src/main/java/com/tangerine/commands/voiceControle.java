package com.tangerine.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class voiceControle extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String SubCommand = event.getInteraction().getSubcommandName();
        assert SubCommand != null;
        if (command.equals("voice")) {
            OptionMapping userOption = event.getOption("user");
            assert userOption != null;
            Member user = userOption.getAsMember();
            assert user != null;
            switch (SubCommand) {
                case "mute":
                    try {
                        if (!Objects.requireNonNull(user.getVoiceState()).isMuted()) {
                            user.mute(true).queue();
                            EmbedBuilder Success = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Voice muted " + user.getAsMention());
                            event.replyEmbeds(Success.build()).setEphemeral(true).queue();
                        } else {
                            EmbedBuilder Err = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Couldn't voice mute " + user.getAsMention());
                            event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                        }
                    } catch (Exception e) {
                        EmbedBuilder Err = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Couldn't voice mute " + user.getAsMention());
                        event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                    }
                    break;
                case "deafen":
                    try {
                        if (!Objects.requireNonNull(user.getVoiceState()).isDeafened()) {
                            user.deafen(true).queue();
                            EmbedBuilder Success = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Voice deafened " + user.getAsMention());
                            event.replyEmbeds(Success.build()).setEphemeral(true).queue();
                        } else {
                            EmbedBuilder Err = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Couldn't voice deafen " + user.getAsMention());
                            event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                        }
                    } catch (Exception e) {
                        EmbedBuilder Err = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Couldn't voice deafen " + user.getAsMention());
                        event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                    }
                    break;
                case "move":
                    try {
                        OptionMapping channelOption = event.getOption("voice");
                        assert channelOption != null;
                        VoiceChannel voice = channelOption.getAsChannel().asVoiceChannel();
                        user.getGuild().moveVoiceMember(user, voice).queue();
                        EmbedBuilder Success = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Moved " + user.getAsMention() + " to " + voice.getAsMention());
                        event.replyEmbeds(Success.build()).setEphemeral(true).queue();
                    } catch (Exception e) {
                        EmbedBuilder Err = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Couldn't move " + user.getAsMention());
                        event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                    }
                    break;
                case "unmute":
                    try {
                        if (Objects.requireNonNull(user.getVoiceState()).isMuted()) {
                            user.mute(false).queue();
                            EmbedBuilder Success = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Voice unMuted " + user.getAsMention());
                            event.replyEmbeds(Success.build()).setEphemeral(true).queue();
                        } else {
                            EmbedBuilder Err = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Couldn't voice unMute " + user.getAsMention());
                            event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                        }
                    } catch (Exception e) {
                        EmbedBuilder Err = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Couldn't voice unmute " + user.getAsMention());
                        event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                    }
                    break;
                case "undeafen":
                    try {
                        if (Objects.requireNonNull(user.getVoiceState()).isDeafened()) {
                            user.deafen(false).queue();
                            EmbedBuilder Success = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Voice unDeafened " + user.getAsMention());
                            event.replyEmbeds(Success.build()).setEphemeral(true).queue();
                        } else {
                            EmbedBuilder Err = new EmbedBuilder()
                                    .setColor(Color.decode("#2F3136"))
                                    .setDescription("Couldn't voice unDeafen " + user.getAsMention());
                            event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                        }
                    } catch (Exception e) {
                        EmbedBuilder Err = new EmbedBuilder()
                                .setColor(Color.decode("#2F3136"))
                                .setDescription("Couldn't voice unDeafen " + user.getAsMention());
                        event.replyEmbeds(Err.build()).setEphemeral(true).queue();
                    }
                    break;
            }
        }
    }
}
