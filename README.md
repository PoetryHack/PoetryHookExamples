# PoetryHookExamples
this repo contains examples for PoetryHook

to compile everything, run
```shell
mvn package -Pbuild-all
```

to run the application, run
```shell
java -jar target/PoetryHookExampleApp.jar
```

to inject the agent, run
```shell
java -jar target/PoetryHookExampleInjector.jar <agent-path> inject <pid>
```
with `<agent-path>` being the path to the agent (a good default is `target/PoetryHookExampleAgent-1.0.0.jar`) and `<pid>` being the process id on your system of the running example app. it is printed on startup by the app.

to eject the agent, run
```shell
java -jar target/PoetryHookExampleInjector.jar <agent-path> eject <pid>
```
