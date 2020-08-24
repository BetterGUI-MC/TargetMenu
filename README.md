### [Video](https://youtu.be/fH0l4whkHJQ)

## Menu Types
* `target`: An expansion of Simple Menu with the target variables
  * Open Command: 
    * `/<menu_command> <target_player>`
    * `/openmenu <menu_file> <player> <target_player>`
* `target-args`: An expansion of Args Menu with the target variables
  * Open Command:
    * `/<menu_command> <target_player> [args]`
    * `/openmenu <menu_file> <player> <target_player> [args]`
***
## Target Variable
* The format: `{target_<variable_name>}`
* `<variable_name>`: the name of the variable without brackets `{}`
  * Example:
    * `{player}` -> `{target_player}`
    * `{ping}` -> `{target_ping}`
    * `{exp_to_level}` -> `{target_exp_to_level}`
## Target PlaceholderAPI placeholders
* The format: `{target_papi_<placeholder_name>}`
* `{placeholder_name}`: the name of the placeholders without `%`
  * Example:
    * `%player_name%` -> `{target_papi_player_name}`
    * `%player_ping%` -> `{target_papi_player_ping}`
    * `%player_uuid%` -> `{target_papi_player_uuid}`
***
## Example Menu
```yaml
menu-settings:
  name: "&c&lTest Target &f&l| &a&lCurrent: &4&l{target_player}"
  menu-type: target
  command: target
  rows: 1
  
info:
  slot: 0
  name: "&bTarget Info"
  id: paper
  lore:
  - "&dWorld: &f{target_world}"
  - "&dPing: &f{target_ping}"
  - "&dLocation: &f{target_x} {target_y} {target_z}"
  
fly:
  slot: 1
  name: "&bToggle Fly"
  id: feather
  command: "console: fly {target_player}"
```
