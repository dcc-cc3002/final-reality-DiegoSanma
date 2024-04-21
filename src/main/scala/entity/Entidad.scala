package entity

/**A trait that includes the methods that will be included in any Entidad
 */
trait Entidad{
  def getName(): String
  def getHp(): Int
  def getDefense():Int
  def getWeight():Int
}
