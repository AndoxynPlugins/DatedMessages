PluginRequest-DisplayNameMessages
=================================

Message changer plugin that includes a date and other things.

Simple Message changer plugin. No commands. No permissions.

This plugin displays the text in the file 'login.txt' and 'logout.txt' to every player on login/logout.

Special variables for *.txt:
* & color codes work. & will be replaced with the color code character, \u00A7. This basically means you can use something like &1 for blue or &2 for green.
* %server will be replaced with the server's name as defined in server.properties.
* %player will be replaced with the player's username.
* %date will be replaced with the current date, in the format of MM/DD/YYYY
* %time will be replaced with the current time, in the format of HH:MM (12 hour format, not 42).
* %ampm will be replaced with either am or pm, depending on the time.
* %AMPM will be replaced with either AM or PM, depending on the time.
* %online will be replaced with a list of the currently online players (including the player who just joined), in the format of `player1, player2, player3`.