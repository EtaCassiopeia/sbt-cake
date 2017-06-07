// Copyright: 2017 https://github.com/cakesolutions/sbt-cake/graphs
// License: http://www.apache.org/licenses/LICENSE-2.0

package net.cakesolutions

import sbt._
import sbt.Keys._

/**
  * Cake recommended settings for Java application packaging.
  */
// only for projects that use the JavaServerAppPackaging
object CakeJavaAppPlugin extends AutoPlugin {
  import com.typesafe.sbt.SbtNativePackager._
  import com.typesafe.sbt.packager.linux.Mapper._

  /** @see http://www.scala-sbt.org/0.13/api/index.html#sbt.package */
  override def requires: Plugins =
    com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging

  /** @see http://www.scala-sbt.org/0.13/api/index.html#sbt.package */
  override def trigger: PluginTrigger = allRequirements

  /** @see http://www.scala-sbt.org/0.13/api/index.html#sbt.package */
  override def projectSettings: Seq[Setting[_]] = Seq(
    mappings in Universal ++= {
      val jar = (packageBin in Compile).value // forces compile
      val src = sourceDirectory.value
      packageMapping((src / "main" / "resources") -> "conf")
        .withContents()
        .mappings
        .toSeq
    }
  )
}
