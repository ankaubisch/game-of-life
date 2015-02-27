package de.kaubisch.kata

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class GameOfLifeSpec extends FlatSpec with Matchers {
  "Game of Life" should "has one alive cell when one cell is placed" in {
    GameOfLife()
      .addCell(Alive(1, 1))
      .hasCell(Alive(1, 1)) should be === true
  }

  it should "kill an alive cell after next turn" in {
    GameOfLife()
      .addCell(Alive(1, 1))
      .makeTurn
      .hasCell(Dead(1,1)) should be === true
  }

  it should "keep alive a cell after next turn when it has 2 neighbours" in {
    GameOfLife()
      .addCell(Alive(1, 1))
      .addCell(Alive(2, 1))
      .addCell(Alive(1, 2))
      .makeTurn
      .hasCell(Alive(1, 1)) should be === true
  }

  it should "keep alive a cell after next turn when it has 3 neighbours" in {
    GameOfLife()
      .addCell(Alive(1, 1))
      .addCell(Alive(2, 1))
      .addCell(Alive(1, 2))
      .addCell(Alive(0, 1))
      .makeTurn
      .hasCell(Alive(1,1)) should be === true
  }

  it should "kills an alive cell after net turn when it has more then 3 neighbours" in {
    GameOfLife()
      .addCell(Alive(1, 1))
      .addCell(Alive(2, 1))
      .addCell(Alive(1, 2))
      .addCell(Alive(0, 1))
      .addCell(Alive(0, 2))
      .makeTurn
      .hasCell(Dead(1,1)) should be === true
  }

  it should "reborn a dead cell after next turn when it has exact 3 neighbours" in {
    GameOfLife()
      .addCell(Alive(1,1))
      .addCell(Alive(2,1))
      .addCell(Alive(1,2))
      .addCell(Dead(2,2))
      .makeTurn
      .hasCell(Alive(2,2)) should be === true
  }

  it should "create a blinker after next turn when it has correct cells set" in {
    val game = GameOfLife()
      .addCell(Alive(0,0))
      .addCell(Alive(1,0))
      .addCell(Alive(2,0))
      .addCell(Dead(1,1))
      .addCell(Dead(1,-1))
      .makeTurn

      game.hasCell(Alive(1, 1)) should be === true
      game.hasCell(Alive(1,-1)) should be === true
      game.hasCell(Dead(0,0)) should be === true
      game.hasCell(Dead(2,0)) should be === true
  }
}
