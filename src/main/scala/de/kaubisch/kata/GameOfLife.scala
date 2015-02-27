package de.kaubisch.kata

sealed trait Position {
  val x: Int
  val y: Int
}
case class Alive(x: Int, y: Int) extends Position
case class Dead(x: Int, y: Int) extends Position

class GameOfLife(positions: Seq[Position]) {
  def makeTurn() : GameOfLife =  new GameOfLife(positions.map(getNewStatusOfCell))
  def hasCell(pos: Position) : Boolean = positions.contains(pos)
  def addCell(pos: Position) : GameOfLife = new GameOfLife(positions :+ pos)

  private def getNewStatusOfCell(pos: Position) : Position = (pos, getCountOfNeighbours(pos)) match {
      case (Alive(_,_),2) | (_,3) => Alive(pos.x, pos.y)
      case _ => Dead(pos.x, pos.y)
    }

  private def getCountOfNeighbours(pos: Position) : Int = {
    def hasNeighbour(posOfNeighbour : Position) : Int = if (pos != posOfNeighbour && hasCell(posOfNeighbour)) 1 else 0
    (for(
      x <- pos.x - 1 to pos.x + 1;
      y <- pos.y - 1 to pos.y + 1
    ) yield hasNeighbour(Alive(x, y))).sum
  }
}

object GameOfLife {
  def apply() : GameOfLife = new GameOfLife(Nil)
}