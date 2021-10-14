![Icon](https://github.com/Zeniku/HeavyMachinery/blob/main/icon.png)<br />

[![GitHub](https://img.shields.io/github/license/Zeniku/HeavyMachineryJava?color=success&label=License&logo=github&style=flat-square)](https://github.com/Zeniku/HeavyMachineryJava/blob/master/LICENSE)
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FZenike%2FHeavyMachineryJava&count_bg=%2379C83D&title_bg=%23555555&icon=codeforces.svg&icon_color=%23E7E7E7&title=visitors&edge_flat=true)](https://hits.seeyoufarm.com)
[![Stars](https://img.shields.io/github/stars/Zeniku/HeavyMachineryJava?label=Star%20this%20Mod%21&style=social)](https://github.com/Zeniku/HeavyMachineryJava/blob/master)

## HeavyMachineryJava
A Java Mindustry mod ported from js to java

<details><summary><b>Other Stuff</b></summary>

- **Wiki:** https://github.com/Zeniku/HeavyMachinery-Wiki
- **JS:** https://github.com/Zeniku/HeavyMachinery
</details>


**Adds:**
<blockquote>

*Units: 11*
 - Melee: 5
 - PurplebAir: 3
 - Overseer: 3

*Blocks: 5*
 - Turrets: 1
 - Effects: 3
 - Production: 1
</blockquote>

### Notes
This Mod is still incomplete
There is a lot planned on this mod but not out yet so keep watch for new updates<br />
All things on `Adds` is subject to change

Thanks you to [Shit Fabric](https://github.com/Duvent-mindustry) For the Awesome Logo!

## Contribute

**For Bugs and issues you may report them To:**
1. [My Discord Sever](https://discord.gg/bWBGyty)
2. [Github's Issues](https://github.com/Zeniku/HeavyMachineryJava/issues)

**For Contribution and ideas**
1. [My Discord Sever](https://discord.gg/bWBGyty)
2. [Github's PullRequest](https://github.com/Zeniku/HeavyMachineryJava/pulls)

##Maintainers

### Owner
Github: [Zeniku](https://github.com/Zeniku)<br />
Discord: `Zeniku/HeavyMachinery#1408`

### Contributors

1. **Shit Fabric**
    - Github: [Shit Fabric](https://github.com/Duvent-mindustry)
    - Discord: `Shit Fabric#6688`
2. **Broad**
    - Github: [Broad](https://github.com/Br0ad)
    - Discord: `Ɓroad#0648`

---
<details>
  <summary><h2>Building</h2></summary>

### Building for Desktop Testing

1. Install JDK **16**.
2. Run `gradlew jar` [1].
3. Your mod jar will be in the `build/libs` directory. **Only use this version for testing on desktop. It will not work with Android.**
To build an Android-compatible version, you need the Android SDK. You can either let Github Actions handle this, or set it up yourself. See steps below.

### Building through Github Actions

This repository is set up with Github Actions CI to automatically build the mod for you every commit. This requires a Github repository, for obvious reasons.
To get a jar file that works for every platform, do the following:
1. Make a Github repository with your mod name, and upload the contents of this repo to it. Perform any modifications necessary, then commit and push. 
2. Check the "Actions" tab on your repository page. Select the most recent commit in the list. If it completed successfully, there should be a download link under the "Artifacts" section. 
3. Click the download link (should be the name of your repo). This will download a **zipped jar** - **not** the jar file itself [2]! Unzip this file and import the jar contained within in Mindustry. This version should work both on Android and Desktop.

### Building Locally

Building locally takes more time to set up, but shouldn't be a problem if you've done Android development before.
1. Download the Android SDK, unzip it and set the `ANDROID_HOME` environment variable to its location.
2. Make sure you have API level 30 installed, as well as any recent version of build tools (e.g. 30.0.1)
3. Add a build-tools folder to your PATH. For example, if you have `30.0.1` installed, that would be `$ANDROID_HOME/build-tools/30.0.1`.
4. Run `gradlew deploy`. If you did everything correctlly, this will create a jar file in the `build/libs` directory that can be run on both Android and desktop. 

--- 

*[1]* *On Linux/Mac it's `./gradlew`, but if you're using Linux I assume you know how to run executables properly anyway.*  
*[2]: Yes, I know this is stupid. It's a Github UI limitation - while the jar itself is uploaded unzipped, there is currently no way to download it as a single file.*
</details>
