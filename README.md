# PoetryHookExamples
this repo contains examples for PoetryHook

to compile everything, run
```shell
mvn package -Pbuild-app
mvn package -Pbuild-agent
mvn package -Pbuild-injector
```

to run the application, run
```shell
java -jar target/PoetryHookExampleApp.jar
```

to let the injector know where the agent is, run
```shell
export POETRYHOOK_EXAMPLE_AGENT_PATH=target/PoetryHookExampleAgent-1.0.0.jar
```
this only needs to be run once per shell environment where you are running the injector

to inject the agent, run
```shell
java -jar target/PoetryHookExampleInjector.jar <pid>
```
with `<pid>` being the process id on your system of the running example app. it is printed on startup by the app.

to eject the agent, run
```shell
java -jar target/PoetryHookExampleInjector.jar <pid> <anything>
```
with `<anything>` being any text that would be passed as an argument to `main(String[] args)`. a future-proof example is `eject`