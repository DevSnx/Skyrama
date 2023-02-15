##
![screenshot](http://diecrewline.de/spigot/Skyrama/Skyrama.png)
![screenshot](http://diecrewline.de/spigot/Skyrama/v1.0.0-ALPHA.png)

## Installation
1. Compile the project or download [this JAR file](https://github.com/DevSnx/Skyrama/releases/download/v0.3-ALPHA/skyrama-0.3-ALPHA.jar).
2. Download latest Worldedit [this JAR file](https://dev.bukkit.org/projects/worldedit/files/latest).
3. Download Island Schematic [this .schem file](http://diecrewline.de/spigot/Skyrama/Schematic/island.schem).
4. Place the plugins into your server plugin's directory.
5. Restart your server or reload it with the command `/reload`.
6. Place the `Island.schem` file into you server `/plugins/Skyrama/schematics` directory.
7. Restart your server or reload it again with the command `/reload`.
8. You can now configure the plugin in the `config.yml` file and messages in `en_US` or `de_DE`.

## Commands

| Command       | Description  |
| ------------- |-------------|
| `/is` or `/island`       | Show the help menu with all the commands. |
| `/is create`      | Create an island.      |
| `/is home` | Teleport user to his island spawn.      |
| `/is setspawn` | Change the island spawn to user position. |
| `/is visit <Player Name>` | Teleport to the specified player island. |
| `/is invite <Player Name>` | Invite the player to play on your island. |
| `/is accept <Player Name>` | Accept the player invitation. |
| `/is deny <Player Name>` | Decline the player invitation. |

## Permissions

| Permissions       | Description  |
| ------------- |-------------|
| `skyrama.*` | All Permissions |
| `skyrama.invite` | Invite the player to play on your island. |
| `skyrama.invite.accept` | Accept the player invitation. |
| `skyrama.invite.deny` | Decline the player invitation. |
| `skyrama.create` | Create an Island. |
| `skyrama.home` | Teleport user to his island spawn. |
| `skyrama.visit` | Teleport to the specified player island. |
| `skyrama.setspawn` | Change the island spawn to user position. |
| `skyrama.break` | Break Blocks on a other Island without Trust |
| `skyrama.place` | Place Blocks on a other Island without Trust |

## Screenshots
![screenshot](https://zupimages.net/up/21/27/c38w.png)
