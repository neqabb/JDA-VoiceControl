package com.tangerine;

import com.tangerine.commands.voiceControle;
import com.tangerine.events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class tangerine {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault("Your Token")
                .setActivity(Activity.watching("your mom"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(
                        new SlashCommandRegistration(),
                        new voiceControle()
                )
                .enableIntents(EnumSet.allOf(GatewayIntent.class))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build().awaitReady();
    }
}

