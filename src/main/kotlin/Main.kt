package org.example

import org.example.particles.*

fun main() {
  val particles = listOf(
    Particle(10.0, 20.0, 0.0, -1.0, 7.0, '*'),
    Particle(30.0, 20.0, 0.0, 1.0, 7.0, '0'),
    Particle(20.0, 10.0, 1.0, 0.0, 7.0, '#'),
    Particle(20.0, 30.0, -1.0, 0.0, 7.0, '@')
  )

  while (true) {
    clearConsole()
    val forces= calculateParticlesGravity(particles)
    updateParticlesPosition(particles, forces)
    drawParticles(particles, 60)
    Thread.sleep(1)
  }






}