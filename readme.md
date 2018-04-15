## expo-template

A collection of sample react native components in Clojurescript.


### Usage

#### Install Expo [XDE and mobile client](https://docs.expo.io/versions/v15.0.0/introduction/installation.html)
    If you don't want to use XDE (not IDE, it stands for Expo Development Tools), you can use [exp CLI](https://docs.expo.io/versions/v15.0.0/guides/exp-cli.html).

``` shell
    yarn global add exp
```

#### Install [Lein](http://leiningen.org/#install) or [Boot](https://github.com/boot-clj/boot)

#### Install npm modules

``` shell
    yarn install
```

#### Signup using exp CLI

``` shell
    exp signup
```

#### Start the figwheel server and cljs repl

##### leiningen users
``` shell
    lein figwheel
```

#### Start Exponent server (Using `exp`)

##### Also connect to Android device

``` shell
    exp start -a --lan
```

##### Also connect to iOS Simulator

``` shell
    exp start -i --lan
```
