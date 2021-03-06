/* Copyright 2018 Braedon Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.smithb99.spambot.command;

import com.smithb99.spambot.Bot;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("ping", new CommandPing());
        commands.put("spam", new CommandSpam());
    }

    @EventSubscriber
    @SuppressWarnings("unused")
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");

        if (!argArray[0].startsWith(Bot.getBotPrefix())) {
            return;
        }

        String command = argArray[0].substring(1);
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);

        if (commands.containsKey(command)) {
            commands.get(command).runCommand(event, argsList);
        }
    }

    @EventSubscriber
    @SuppressWarnings("unused")
    public void onShardReady(ReadyEvent event) {

    }
}
