package org.example.particles

import kotlin.math.sqrt

const val G = 0.01
const val DT = 0.1

fun drawParticles(particles:List<Particle>,size:Int) {
  val canvas = createCanvas(particles,size)
  for (row in canvas) {
    println(row.joinToString(""))
  }
}

fun createCanvas(particles:List<Particle>, size:Int):Array<CharArray> {
  val canvas = Array(size) { CharArray(size) {' '} }
  for (particle in particles) {
    val x=particle.x.toInt().coerceIn(0,size-1)
    val y=particle.y.toInt().coerceIn(0,size-1)
    canvas[y][x] = particle.symbol
  }
  return canvas
}

fun calculateParticlesGravity(particles:List<Particle>):List<Pair<Double, Double>>{
  val particleForces=mutableListOf<Pair<Double, Double>>()
  for (i in particles.indices) {
    var Fx=0.0
    var Fy=0.0

    for (j in particles.indices) {
      if (i!=j) {
        val dx = particles[j].x - particles[i].x
        val dy = particles[j].y - particles[i].y
        val dist = dx * dx + dy * dy
        val gravity = (G * particles[i].mass * particles[j].mass) / (dist)

        Fx += gravity * dx / sqrt(dist)
        Fy += gravity * dy / sqrt(dist)
      }
    }
    particleForces.add(Pair(Fx,Fy))
  }
  return particleForces
}

fun updateParticlesPosition(particles:List<Particle>, particleForces:List<Pair<Double, Double>>) {
  for (i in particles.indices) {
    val Fx=particleForces[i].first
    val Fy=particleForces[i].second

    particles[i].vx+=Fx/(particles[i].mass* DT)  //F=ma -> ma/m*t=a/t=v
    particles[i].vy+=Fy/(particles[i].mass* DT)

    particles[i].x+=particles[i].vx*DT
    particles[i].y+=particles[i].vy*DT

  }
}

fun clearConsole() {
  print("\u001b[H\u001b[2J")
}