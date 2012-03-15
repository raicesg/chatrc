// sbt plugins, any general dependencies, and any necessary repositories

// sbt-onejar

resolvers += "retronym-releases" at "http://retronym.github.com/repo/releases"

resolvers += "retronym-snapshots" at "http://retronym.github.com/repo/snapshots"

addSbtPlugin("com.github.retronym" % "sbt-onejar" % "0.6")

// sbt-dependency-graph

addSbtPlugin("net.virtualvoid" % "sbt-dependency-graph" % "0.5.1")


