# Android TPS Task Briefing
## Introduction
This project is a starting point in your Android interview journey with DoorDash. At the first Phone screen stage you will start off by adding the initial functionality here and continue adding to it throughout the entire interview process. So keep the progress saved as you move forward.

Here are a few key points to pay attention to in the project:
1. UI implementation
    * A starting Activity is added and it displays a `StoreFeedFragment`
    * `StoreFeedAdapter` is wired up with a `item_store.xml` layout file and just needs some final touches to be made to complete its implementation (see TODOs)
2. Dagger
    * We use Dagger for dependency injection (DI) within the app
    * You are free to modify the DI approach to the one you are most comfortable with
3. Network
    * Network stack is covered by Retrofit2
    * `TPSService` located in the `network` package outlines the backend API we will be hitting during our interview
    * An instance of `TPSService` is provided by Dagger via `NetworkModule` and can be injected anywhere
4. `ViewModelFactory` (in case if you will go MVVM route)
    * `ViewModelFactory` implementation is provided to save time dealing with DI and `ViewModelProvider.Factory` implementation
    * Usage code snippet is provided in the Javadoc of the `ViewModelFactory.kt` file

> ### Please Note:
> This skeleton project is provided for your convenience and you are free to modify it as you see fit as you complete the task during the interview, as long as it does not impact the end goal of the interview - a well architected, clean and working solution.
> 
> Just a friendly advise is that if you are comfortable with the generic implementation of this project - then just stick to it, as we will need to move fast in order to complete the task in less then 60 minutes.

## IDE Requirements
This project runs with Android Studio Flamingo and above

## Grading & Further Expectations
We are looking for the Architectural consistency, conciseness and cleanliness of the code. Make sure that the code you produce is testable and be ready to write Unit Tests for your code if you will be asked to do so.

As mentioned above, you will be making additions to this project throughout the interview process going further so make sure you save the progress and have something you can work well with during the follow up stages.

Good luck and we are looking forward to meet you!

## Environment Setup Tips
### Java 11 / 17
[Azul Zulu ARM64 JDK 11](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk) download for M1 macs.

[Azul Zulu JDK 17](https://www.azul.com/downloads/?version=java-17-lts&os=macos&architecture=arm-64-bit&package=jdk) download for M1 macs.

### jenv
We highly recommend using [jenv](https://www.jenv.be/) to manage your different JDKs.

Follow the instructions below to set up jenv after you’ve installed java above.

1. Install jenv

Linux / OS X
```bash
$ git clone https://github.com/jenv/jenv.git ~/.jenv
```

Mac OS X via [Homebrew](http://brew.sh/)
```bash
$ brew install jenv
```

2. Add to path

```bash
$ echo 'export PATH="$HOME/.jenv/bin:$PATH"' >> ~/.zsh_profile
$ echo 'eval "$(jenv init -)"' >> ~/.zsh_profile
$ source ~/.zsh_profile
```

3. Run plugin to set your `JAVA_HOME` environment variable automatically

```bash
$ jenv enable-plugin export
$ source ~/.zsh_profile
```

4. Add jdk to jenv

```bash
# M1 Mac
jenv add /Library/Java/JavaVirtualMachines/zulu-17.jdk/Contents/Home
# Intel Mac
jenv add /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home
```
Note for M1 Macs: if you don’t have `zulu-17.jdk`, download it from the link below and add it to `/Library/Java/JavaVirtualMachines`
[Azul Downloads](https://www.azul.com/downloads/?version=java-17-lts&os=macos&architecture=arm-64-bit&package=jdk#zulu)

5. Set your global version

```bash
# M1 Mac
$ jenv global 17.0
# Intel Mac
$ jenv global 11.0
```

jenv reads the `.java-version` file for each repo automatically
